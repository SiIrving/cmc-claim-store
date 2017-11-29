package uk.gov.hmcts.cmc.domain.models.response;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import uk.gov.hmcts.cmc.domain.models.ccj.PaymentSchedule;
import uk.gov.hmcts.cmc.domain.models.constraints.DateNotInThePast;
import uk.gov.hmcts.cmc.domain.models.constraints.Money;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static uk.gov.hmcts.cmc.domain.utils.ToStringStyle.ourStyle;

public class DefendantPaymentPlan {

    @NotNull
    @Money
    @DecimalMin(value = "0.01")
    private final BigDecimal firstPayment;

    @NotNull
    @Money
    @DecimalMin(value = "0.01")
    private final BigDecimal instalmentAmount;

    @NotNull
    @DateNotInThePast
    private final LocalDate firstPaymentDate;

    @NotNull
    private final PaymentSchedule paymentSchedule;

    @NotNull
    @Size(max = 255, message = "must be at most {max} characters")
    private final String explaination;

    public DefendantPaymentPlan(
        final BigDecimal firstPayment,
        final BigDecimal instalmentAmount,
        final LocalDate firstPaymentDate,
        final PaymentSchedule paymentSchedule,
        final String explaination
    ) {

        this.firstPayment = firstPayment;
        this.instalmentAmount = instalmentAmount;
        this.firstPaymentDate = firstPaymentDate;
        this.paymentSchedule = paymentSchedule;
        this.explaination = explaination;
    }

    public BigDecimal getFirstPayment() {
        return firstPayment;
    }

    public BigDecimal getInstalmentAmount() {
        return instalmentAmount;
    }

    public LocalDate getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    public String getExplaination() { return explaination; }


    @Override
    @SuppressWarnings("squid:S1067") // Its generated code for equals sonar
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        uk.gov.hmcts.cmc.domain.models.response.DefendantPaymentPlan that = (uk.gov.hmcts.cmc.domain.models.response.DefendantPaymentPlan) other;
        return Objects.equals(firstPayment, that.firstPayment)
            && Objects.equals(instalmentAmount, that.instalmentAmount)
            && Objects.equals(firstPaymentDate, that.firstPaymentDate)
            && Objects.equals(paymentSchedule, that.paymentSchedule)
            && Objects.equals(explaination, that.explaination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPayment, instalmentAmount, firstPaymentDate, paymentSchedule, explaination);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ourStyle());
    }
}

