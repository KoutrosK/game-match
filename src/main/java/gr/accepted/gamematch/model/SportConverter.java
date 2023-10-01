package gr.accepted.gamematch.model;

import java.util.stream.Stream;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SportConverter implements AttributeConverter<Sport, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Sport attribute) {

		if (attribute == null)
			return null;

		return attribute.getCode();
	}

	@Override
	public Sport convertToEntityAttribute(Integer code) {

		if (code == null)
			return null;

		return Stream.of(Sport.values()).filter(c -> c.getCode().equals(code)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
