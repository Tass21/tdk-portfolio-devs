package com.tdksoft.batch.congig;

import com.tdksoft.batch.student.Student;
import org.springframework.batch.item.ItemProcessor;

/**
 * Will map the input format into output format
 *
 * Input and output ay differ in a real wold project. E.g : Person list to be map to student format
 * @Input format Student
 * @Output  fornat Student
 *
 */
public class StudentItemPocessor implements ItemProcessor<Student, Student> {

    /**
     *
     * @param item to be processed, never {@code null}.
     * @return
     * @throws Exception
     */
    @Override
    public Student process(Student item) throws Exception {
        return item;
    }
}
