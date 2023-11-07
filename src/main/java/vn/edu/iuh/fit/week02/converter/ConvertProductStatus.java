package vn.edu.iuh.fit.week02.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import vn.edu.iuh.fit.week02.status.ProductStatus;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ConvertProductStatus implements AttributeConverter<ProductStatus, Integer>{
    @Override
    public Integer convertToDatabaseColumn(ProductStatus attribute) {
        return attribute == null ? null : attribute.getValue();
    }

    @Override
    public ProductStatus convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null :
                Stream.of(ProductStatus.values()).filter(value -> value.getValue() == dbData).findFirst()
                        .orElseThrow(IllegalArgumentException::new);
    }
}
