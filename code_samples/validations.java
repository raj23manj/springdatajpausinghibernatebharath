
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@Setter
@ValidSystematicPlanRequest
public class BaseTransactionPlanRequest implements Serializable {

  @NotEmpty
  @ValidInvestmentAccount
  private String mfInvestmentAccount;

  @NotNull
  @CheckEnum(PlanFrequency.class)
  @Pattern(regexp = "monthly|quarterly", message = "value entered is not allowed", flags = {Pattern.Flag.CASE_INSENSITIVE})
  private String frequency;

  @NotNull
  private BigDecimal amount;

  // note: when daily frequency is supported, must allow null
  @NotNull(message = "must not be null")
  @Min(1)
  @Max(28)
  private Integer installmentDay;

  // note: when sporadic frequency is supported, must allow null
  @NotNull
  @Min(1)
  private Integer numberOfInstallments;

  protected String partner;

  private String euin;

  private String sourceRefId;

  private String userIp;

  private String serverIp;

  @Pattern(regexp = "distributor|investor", message = "Should be either distributor or investor", flags = {Pattern.Flag.CASE_INSENSITIVE})
  private String initiatedBy;

  @Pattern(regexp = "mobile_app|mobile_app_android|mobile_app_ios|mobile_web_android|mobile_web_ios|mobile_web|web",
      message = "Should be either mobile_app or mobile_app_android or mobile_app_ios or mobile_web_android or mobile_web_ios or mobile_web or web",
      flags = {Pattern.Flag.CASE_INSENSITIVE})
  private String initiatedVia;

  @NotNull
  private Boolean systematic;

  @AssertTrue(message = "manual generation of installments not supported")
  private boolean autoGenerateInstallments = true;

  private boolean generateFirstInstallmentNow;

  private String activateAfter = null;
}
