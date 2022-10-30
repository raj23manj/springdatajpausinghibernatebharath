public Page<Plan> getLatest100Plans(List<Folio> folios, List<PlanStatus> statuses,
                                      List<OrderType> orderTypes) {
    Pageable pageable = PageRequest.of(0, 100);
    PlanFinder planFinder = new PlanFinder(entityManager);
    return planFinder.init()
        .inFolio(folios)
        .inOrderType(orderTypes)
        .inStatus(statuses)
        .inDirection("desc")
        .getResults(pageable);
  }
