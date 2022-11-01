  @GetMapping
  public ResponseEntity<Object> search(@RequestParam(value = "mf_investment_account", required = false) String mfInvestmentAccount,
                                       @Pattern(regexp = "created|active|pending|cancelled|completed",
                                           message = "Should be either created or active or cancelled or completed or pending",
                                           flags = {Pattern.Flag.CASE_INSENSITIVE}) @RequestParam(value = "states", required = false) String states) {
    List<Plan> plans = purchasePlanService.search(mfInvestmentAccount, states);
    ListResponse<PurchasePlanResponse> response = ListResponse.<PurchasePlanResponse>builder()
        .data(plans.stream().map(PurchasePlanResponse::new).toList())
        .build();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
