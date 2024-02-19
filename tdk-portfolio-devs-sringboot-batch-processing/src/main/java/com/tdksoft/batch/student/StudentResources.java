package com.tdksoft.batch.student;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tdksoft.batch.utils.Constants.JOB_START_AT;

@RestController
@RequestMapping("/api/students")
public class StudentResources {

    private final JobLauncher jobLauncher;
    private final Job job;

    public StudentResources(JobLauncher jobLauncher, Job job){
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @PostMapping
    public void iportCsvToDBJob(){
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong(JOB_START_AT, System.currentTimeMillis()) // Strat immediately when calling the endpoint
                .toJobParameters();
        try {
            jobLauncher.run(job,jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobParametersInvalidException |
                 JobInstanceAlreadyCompleteException | JobRestartException e) {
            //throw new RuntimeException(e);
            e.printStackTrace(); // todo lo this issues
        }
    }
}
