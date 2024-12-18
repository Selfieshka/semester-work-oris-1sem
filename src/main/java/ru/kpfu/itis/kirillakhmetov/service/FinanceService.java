package ru.kpfu.itis.kirillakhmetov.service;

import lombok.RequiredArgsConstructor;
import ru.kpfu.itis.kirillakhmetov.dao.FinanceDao;
import ru.kpfu.itis.kirillakhmetov.dto.*;
import ru.kpfu.itis.kirillakhmetov.entity.Finance;
import ru.kpfu.itis.kirillakhmetov.exception.ValidationException;
import ru.kpfu.itis.kirillakhmetov.util.JsonConverter;
import ru.kpfu.itis.kirillakhmetov.util.annotations.SingletonFactory;
import ru.kpfu.itis.kirillakhmetov.validator.CreateExpenseValidator;
import ru.kpfu.itis.kirillakhmetov.validator.CreateRevenueValidator;
import ru.kpfu.itis.kirillakhmetov.validator.ValidationResult;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class FinanceService {
    private final FinanceDao financeDao;
    private static final int LIMIT = 5;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final CreateExpenseValidator createExpenseValidator = SingletonFactory.getInstance(CreateExpenseValidator.class);
    private static final CreateRevenueValidator createRevenueValidator = SingletonFactory.getInstance(CreateRevenueValidator.class);

    public void addRevenue(FinanceDto financeDto) {
        ValidationResult validationResult = createRevenueValidator.isValid(financeDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        financeDao.save(Finance.builder()
                .owner_id(financeDto.owner_id())
                .type("Доход")
                .amount(financeDto.amount())
                .category(financeDto.category())
                .date(financeDto.date())
                .build()
        );
    }

    public void addExpense(FinanceDto financeDto) throws ValidationException {
        ValidationResult validationResult = createExpenseValidator.isValid(financeDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        financeDao.save(Finance.builder()
                .owner_id(financeDto.owner_id())
                .type("Расход")
                .amount(financeDto.amount())
                .category(financeDto.category())
                .date(financeDto.date())
                .build()
        );
    }

    public String calculateRevenue(Long id) {
        double sumRevenue = financeDao.sumAllRevenue(id);
        return JsonConverter.convertToJson(new ApiFinanceDto(sumRevenue));
    }

    public String calculateExpense(Long id) {
        double sumExpense = financeDao.sumAllExpense(id);
        return JsonConverter.convertToJson(new ApiFinanceDto(sumExpense));
    }

    public String calculateProfit(Long idOwner) {
        double profit = financeDao.sumAllRevenue(idOwner) - financeDao.sumAllExpense(idOwner);
        return JsonConverter.convertToJson(new ApiFinanceDto(profit));
    }

    public String analyzeProfit(Long idOwner) {
        List<Finance> finances = financeDao.profitAnalysisByOwnerid(idOwner);
        List<String> dates = new ArrayList<>();
        List<String> amounts = new ArrayList<>();
        String forecastDate = null;
        if (!finances.isEmpty()) {
            for (Finance finance : finances) {
                dates.add(finance.getDate().format(FORMATTER));
                amounts.add(String.valueOf(finance.getAmount()));
            }
            forecastDate = LocalDate.parse(dates.getLast(), FORMATTER).format(FORMATTER);
            dates.add(forecastDate);
        }
        String forecastValue = String.valueOf(calculateAverageDifference(amounts));
        return JsonConverter.convertToJson(new ProfitDto(dates, amounts, forecastDate, forecastValue));
    }

    public String analyzeExpense(Long idOwner) {
        List<Finance> finances = financeDao.expenseAnalysisByOwnerid(idOwner);
        List<String> categories = new ArrayList<>();
        List<String> amounts = new ArrayList<>();
        for (Finance finance : finances) {
            categories.add(String.valueOf(finance.getCategory()));
            amounts.add(String.valueOf(finance.getAmount()));
        }
        return JsonConverter.convertToJson(new ExpenseDto(categories, amounts));
    }

    public String getPage(Long idOwner, int page) {
        List<Finance> finances = financeDao.getPartRevenuesAndExpenses(
                idOwner, LIMIT, LIMIT * (page - 1)
        );
        List<FinancePaginationDto> financePaginationDtos = new ArrayList<>();
        for (Finance finance : finances) {
            financePaginationDtos.add(new FinancePaginationDto(
                    finance.getType(),
                    finance.getAmount(),
                    finance.getCategory(),
                    finance.getDate().format(FORMATTER)
            ));
        }
        return JsonConverter.convertToJson(financePaginationDtos);
    }

    public String getCountItems(Long idOwner) {
        int result = (int) Math.ceil((double) financeDao.countRevenuesAndExpensesByOwnerId(idOwner) / LIMIT);
        return "{\"totalPages\": %s}".formatted(result);
    }

    private double calculateAverageDifference(List<String> numbers) {
        double totalDifference = 0.0;
        int count = 0;
        double sum = 0;
        for (int i = 0; i < numbers.size() - 1; i++) {
            totalDifference += Double.parseDouble(numbers.get(i + 1)) - Double.parseDouble(numbers.get(i));
            sum += Double.parseDouble(numbers.get(i));
            count++;
        }
        return (sum + totalDifference) / count;
    }

    public MonthInfo getMonthInfo(Long id) {
        LocalDate dateNow = LocalDate.now();
        double monthRevenue = financeDao.getSumRevenuesMonthById(id);
        double monthExpenses = financeDao.getSumExpensesMonthById(id);
        double prevMonthRecord = financeDao.getMonthProfitById(id, dateNow.minusMonths(1))
                - financeDao.getMonthProfitById(id, dateNow);
        return new MonthInfo(
                dateNow.format(FORMATTER),
                monthRevenue,
                monthExpenses,
                prevMonthRecord < 0 ? 0 : (double) Math.round(prevMonthRecord * 100) / 100
        );
    }
}
