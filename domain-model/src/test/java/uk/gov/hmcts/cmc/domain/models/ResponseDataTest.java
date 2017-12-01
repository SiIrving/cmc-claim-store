package uk.gov.hmcts.cmc.domain.models;

import org.junit.Test;
import uk.gov.hmcts.cmc.domain.models.sampledata.SampleFullDefenceResponseData;
import uk.gov.hmcts.cmc.domain.models.sampledata.SampleResponseData;
import uk.gov.hmcts.cmc.domain.utils.ResourceReader;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.gov.hmcts.cmc.domain.utils.BeanValidator.validate;

public class ResponseDataTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void shouldHaveNoValidationMessagesWhenResponseDataIsValid() {
        //given
        ResponseData responseData = SampleResponseData.validDefaults();
        //when
        Set<ConstraintViolation<ResponseData>> response = validator.validate(responseData);
        //then
        assertThat(response).isEmpty();
    }

    @Test
    public void shouldHaveValidationMessagesWhenResponseDataElementsAreInValid() {
        //given
        final ResponseData responseData = SampleFullDefenceResponseData.builder()
            .withDefence(null)
            .build();
        //when
        Set<String> errors = validate(responseData);

        //then
        assertThat(errors)
            .hasSize(1)
            .contains("defence : may not be empty");
    }


    @Test
    public void shouldHaveValidationMessagesWhenDefenceDataElementIsEmpty() {
        //given
        final ResponseData responseData = SampleFullDefenceResponseData.builder()
            .withDefence(null)
            .build();

        //when
        Set<String> errors = validate(responseData);

        //then
        assertThat(errors)
            .hasSize(1)
            .contains("defence : may not be empty");
    }

    @Test
    public void shouldHaveValidationMessagesWhenDefenceExceedsSizeLimit() {
        //given
        final String defence = new ResourceReader().read("/defence_exceeding_size_limit.text");

        final ResponseData responseData = SampleFullDefenceResponseData.builder()
            .withDefence(defence)
            .build();

        //when
        Set<String> errors = validate(responseData);

        //then
        assertThat(errors)
            .hasSize(1)
            .contains("defence : size must be between 0 and 99000");
    }
}
