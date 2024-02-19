package com.tdksoft.batch.congig;

import com.tdksoft.batch.student.Student;
import com.tdksoft.batch.student.StudentRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import static com.tdksoft.batch.utils.Constants.AGE_COLUMN;
import static com.tdksoft.batch.utils.Constants.CLASSPATH_STUDENT_UPLOAD_CSV;
import static com.tdksoft.batch.utils.Constants.CSV_READER;
import static com.tdksoft.batch.utils.Constants.DELIMITER;
import static com.tdksoft.batch.utils.Constants.FIRST_NAME_COLUMN;
import static com.tdksoft.batch.utils.Constants.ID_COULUMN;
import static com.tdksoft.batch.utils.Constants.IMPORT_STUDENT_JOB;
import static com.tdksoft.batch.utils.Constants.LAST_NAME;
import static com.tdksoft.batch.utils.Constants.SAVE_METHOD;
import static com.tdksoft.batch.utils.Constants.STUDENT_CSV_IMPORT;

@Configuration
public class BatchConfig {

    private final StudentRepository  studentReposiory;
    private final JobRepository  jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    public BatchConfig(StudentRepository  studentReposiory,
                       JobRepository jobRepository,
                       PlatformTransactionManager platformTransactionManager){
        this.studentReposiory = studentReposiory;
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }
    /**
     *  Item Reader bean
     * @return ite reader
     */

    @Bean
    public FlatFileItemReader<Student> itemReader(){
        FlatFileItemReader itemReader = new FlatFileItemReader() ;
        itemReader.setResource(new FileSystemResource(CLASSPATH_STUDENT_UPLOAD_CSV));
        itemReader.setName(CSV_READER);
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    /**
     * Item Processor bean
     * @return
     */
    @Bean
    public StudentItemPocessor procPocessor(){
        return new StudentItemPocessor();
    }

    /**
     * Item Writter bean
     * @return
     */
    @Bean
    public RepositoryItemWriter<Student> writer(){
        RepositoryItemWriter<Student> writer = new RepositoryItemWriter<>();
        writer.setRepository(studentReposiory);
        writer.setMethodName(SAVE_METHOD); //Call the save method of the Studentrepositoy
        return writer;
    }

    /**
     *
     * @return
     */
    public Step importStep(){
        return
                new StepBuilder(STUDENT_CSV_IMPORT, jobRepository)
                        // Input and output are Student
                        .<Student, Student>chunk(10, platformTransactionManager) //process 10 items at a time
                        .reader(itemReader())
                        .processor(procPocessor())
                        .writer(writer())
                        .build();

    }

    /**
     * Job to be executed
     */
    @Bean
    public Job runJob(){
       return new JobBuilder(IMPORT_STUDENT_JOB, jobRepository)
               .start(importStep())
               .build();
    }

    /**
     *
     * @return
     */
    private LineMapper<Student> lineMapper() {

        DefaultLineMapper<Student> lineMapper = new DefaultLineMapper<>();

        // comma separeted columns
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(DELIMITER);
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(ID_COULUMN, FIRST_NAME_COLUMN, LAST_NAME, AGE_COLUMN);

        // Transform each line read from he csv file to sudne object
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper();
        fieldSetMapper.setTargetType(Student.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }

}
