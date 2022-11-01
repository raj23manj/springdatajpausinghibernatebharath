
import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.base.Strings;
import lombok.Getter;


public enum InstallmentType {
  SYSTEMATIC_WITHDRAWAL_INSTALLMENT("systematic_withdrawal_installment"),
  NORMAL_REDEMPTION_INSTALLMENT("normal_redemption_installment"),
  SYSTEMATIC_TRANSFER_INSTALLMENT("systematic_transfer_installment"),
  NORMAL_SWITCH_INSTALLMENT("normal_switch_installment"),
  SYSTEMATIC_PURCHASE_INSTALLMENT("systematic_purchase_installment"),
  NORMAL_PURCHASE_INSTALLMENT("normal_purchase_installment");


  @Getter
  public final String value;

  InstallmentType(String value) {
    this.value = value;
  }

  public static InstallmentType findByType(String type) {
    if (!Strings.isNullOrEmpty(type)) {
      try {
        return InstallmentType.valueOf(type.toUpperCase());
      } catch (IllegalArgumentException iae) {
        return null;
      }
    }
    return null;
  }

  @JsonCreator
  public static InstallmentType forValue(String v) {
    return findByType(v);
  }
}
