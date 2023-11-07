package vn.edu.iuh.fit.week02.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import vn.edu.iuh.fit.week02.status.EmployeeStatus;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ConvertEmployeeStatus implements AttributeConverter<EmployeeStatus, Integer>{

    @Override
    public Integer convertToDatabaseColumn(EmployeeStatus attribute) {
        return attribute == null ? null : attribute.getValue();
    }

    @Override
    public EmployeeStatus convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null :
                Stream.of(EmployeeStatus.values()).filter(value -> value.getValue() == dbData).findFirst()
                        .orElseThrow(IllegalArgumentException::new);
    }
}
