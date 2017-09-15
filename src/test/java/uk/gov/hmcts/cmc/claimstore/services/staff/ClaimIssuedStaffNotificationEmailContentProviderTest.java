package uk.gov.hmcts.cmc.claimstore.services.staff;

import org.junit.Test;
import uk.gov.hmcts.cmc.claimstore.controllers.utils.sampledata.SampleClaim;
import uk.gov.hmcts.cmc.claimstore.services.staff.models.EmailContent;

import static org.assertj.core.api.Assertions.assertThat;

public class ClaimIssuedStaffNotificationEmailContentProviderTest {

    ClaimIssuedStaffNotificationEmailContentProvider provider = new ClaimIssuedStaffNotificationEmailContentProvider();

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerWhenGivenNullReferenceNumber() {
        // I think this test doesn't make sense. Ref number is generated automatically on insert
        // cannot be null
        provider.createContent(SampleClaim.builder().withReferenceNumber(null).build());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentWhenGivenEmptyReferenceNumber() {
        provider.createContent(SampleClaim.builder().withReferenceNumber("").build());
    }

    @Test
    public void shouldFormatEmailSubjectToExpectedValue() {
        EmailContent emailContent = provider.createContent(SampleClaim.getDefault());
        assertThat(emailContent.getSubject()).isEqualTo("Claim 000CM001 issued");
    }

    @Test
    public void shouldFormatEmailSubjectToExpectedValueForLegal() {
        EmailContent emailContent = provider.createContent(SampleClaim.getDefaultForLegal());
        assertThat(emailContent.getSubject()).isEqualTo("Claim form 000CM001");
    }

    @Test
    public void shouldFormatEmailBodyToExpectedValue() {
        EmailContent emailContent = provider.createContent(SampleClaim.getDefault());
        assertThat(emailContent.getBody()).isEqualTo("Please find attached claim.");
    }
}
