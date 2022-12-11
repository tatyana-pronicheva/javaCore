package gb.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @NoArgsConstructor
    public class StudentDtoWithoutId {
        private String name;
        private Integer age;
    }

