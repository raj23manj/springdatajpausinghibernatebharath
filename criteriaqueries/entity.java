

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class PlanFinder extends BaseFinder {
  private CriteriaBuilder criteriaBuilder;
  private CriteriaQuery<Plan> criteriaQuery;
  private Root<Plan> planRoot;
  private List<Predicate> params;

  private CriteriaQuery<Long> countQuery;
  private Root<Plan> countPlanRoot;

  public PlanFinder(EntityManager entityManager) {
    super(entityManager);
  }

  public PlanFinder init() {
    criteriaBuilder = entityManager.getCriteriaBuilder();

    criteriaQuery = criteriaBuilder.createQuery(Plan.class);
    countQuery = criteriaBuilder.createQuery(Long.class);

    planRoot = criteriaQuery.from(Plan.class);
    countPlanRoot = countQuery.from(Plan.class);

    criteriaQuery = criteriaQuery.select(planRoot);
    countQuery = countQuery.select(criteriaBuilder.count(countPlanRoot));

    params = new ArrayList<>();
    return this;
  }

  public PlanFinder inStatus(List<PlanStatus> statuses) {
    if (statuses != null && !statuses.isEmpty()) {
      Expression<PlanStatus> expPlanStatus = planRoot.get("status");
      Predicate predicatePlanStatus = expPlanStatus.in(statuses);
      params.add(predicatePlanStatus);
    }
    return this;
  }


  public PlanFinder inOrderType(List<OrderType> orderTypes) {
    Expression<PlanStatus> expPlanType = planRoot.get("orderType");
    Predicate predicatePlanType = expPlanType.in(orderTypes);
    params.add(predicatePlanType);
    return this;
  }

  public PlanFinder inFrequency(List<PlanFrequency> frequencies) {
    Expression<PlanStatus> expPlanFrequency = planRoot.get("frequency");
    Predicate predicatePlanFrequency = expPlanFrequency.in(frequencies);
    params.add(predicatePlanFrequency);
    return this;
  }


  public PlanFinder withExpireDateLessThan(LocalDate investmentDate) {
    if (null != investmentDate) {
      params.add(criteriaBuilder.lessThan(planRoot.get("endDate"), investmentDate));
    }
    return this;
  }

  public PlanFinder withAutoGenerateInstallments(boolean autoGenerateInstallments) {
    params.add(criteriaBuilder.lessThan(planRoot.get("autoGenerateInstallments"), autoGenerateInstallments));
    return this;
  }

  public PlanFinder withEntityVersion(String entityVersion) {
    if (entityVersion != null)
      params.add(criteriaBuilder.lessThan(planRoot.get("entityVersion"), entityVersion));
    return this;
  }

  public PlanFinder inBetweenNextInstallmentDate(LocalDate firstDate, LocalDate lastDate) {
    if (null != firstDate && null != lastDate) {
      Expression<LocalDate> tradeDate = planRoot.get("nextOrderInvDate");
      Predicate tradeDateBtw = criteriaBuilder.between(tradeDate, firstDate, lastDate);
      params.add(tradeDateBtw);
    }
    return this;
  }


  public PlanFinder inFolio(List<Folio> folios) {
    if (folios != null && !folios.isEmpty()) {
      Expression<Long> expFolio = planRoot.get("folio");
      Predicate predicateFolio = expFolio.in(folios);
      params.add(predicateFolio);
    }
    return this;
  }

  public PlanFinder inDirection(String direction) {
    if ("asc".equalsIgnoreCase(direction)) {
      criteriaQuery.orderBy(criteriaBuilder.asc(planRoot.get("investmentDate")));
    } else {
      criteriaQuery.orderBy(criteriaBuilder.desc(planRoot.get("investmentDate")));
    }
    criteriaQuery.orderBy(criteriaBuilder.desc(planRoot.get("id")));
    return this;
  }

  public List<Plan> getResults() {
    if (params.size() == 0) return new ArrayList<>();
    criteriaQuery = criteriaQuery.where(params.toArray(new Predicate[0]));
    TypedQuery<Plan> typedQuery = entityManager.createQuery(criteriaQuery);
    return typedQuery.getResultList();
  }

  public Page<Plan> getResults(Pageable pageable) {
    if (params.size() == 0) return new PageImpl<Plan>(new ArrayList<>());
    criteriaQuery = criteriaQuery.where(params.toArray(new Predicate[0]));
    TypedQuery<Plan> query = entityManager.createQuery(criteriaQuery);

    countQuery = countQuery.where(params.toArray((new Predicate[0])));
    TypedQuery<Long> typedCountQuery = entityManager.createQuery(countQuery);
    Long totalRows = typedCountQuery.getSingleResult();


    Page<Plan> result = new PageImpl<Plan>(query.setFirstResult(Math.toIntExact(pageable.getOffset())).setMaxResults(pageable.getPageSize()).getResultList(), pageable, totalRows);
    return result;
  }

}
