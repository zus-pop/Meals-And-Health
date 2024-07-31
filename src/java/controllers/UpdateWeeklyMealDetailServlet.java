package controllers;

import dao.MealDAO;
import dao.WeeklyMealDAO;
import dao.WeeklyMealDetailDAO;
import dto.Meal;
import dto.WeeklyMeal;
import dto.WeeklyMealDetail;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Days;
import utils.MealType;

/**
 *
 * @author hoang
 */
@WebServlet({"/admin-get-update-weekly-detail-view", "/admin-update-weekly-detail"})
public class UpdateWeeklyMealDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WeeklyMealDAO weeklyDao = WeeklyMealDAO.getInstance();
        MealDAO mealDao = MealDAO.getInstance();
        List<Meal> meals = mealDao.findAll();
        int id = Integer.parseInt(request.getParameter("weeklyId"));
        WeeklyMeal weeklyMeal = weeklyDao.findById(id);
        request.setAttribute("weeklyMeal", weeklyMeal);
        request.setAttribute("meals", meals);
        request.getRequestDispatcher("admin-edit-weekly-meal-detail.jsp").forward(request, response);
    }

    private List<Meal> mealMapping(String[] meal) {
        if (meal == null) {
            return new ArrayList<>();
        }
        MealDAO mealDao = MealDAO.getInstance();
        return Arrays.asList(meal)
                .stream()
                .map(id -> mealDao.findById(Integer.parseInt(id)))
                .collect(Collectors.toList());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int weeklyId = Integer.parseInt(request.getParameter("weeklyId"));
        List<WeeklyMealDetail> weeklyList = new ArrayList<>();
        WeeklyMealDAO weeklyDao = WeeklyMealDAO.getInstance();
        WeeklyMealDetailDAO weeklyDetailDao = WeeklyMealDetailDAO.getInstance();
        Map<Days, Map<MealType, List<Meal>>> mealList = weeklyDao.initMealList();

        String[] mondayBreakfast = request.getParameterValues("monday-meal-breakfast");
        String[] mondayLunch = request.getParameterValues("monday-meal-lunch");
        String[] mondayDinner = request.getParameterValues("monday-meal-dinner");
        String[] mondaySnack = request.getParameterValues("monday-meal-snack");

        mealList.get(Days.MONDAY).get(MealType.BREAKFAST).addAll(mealMapping(mondayBreakfast));
        mealList.get(Days.MONDAY).get(MealType.LUNCH).addAll(mealMapping(mondayLunch));
        mealList.get(Days.MONDAY).get(MealType.DINNER).addAll(mealMapping(mondayDinner));
        mealList.get(Days.MONDAY).get(MealType.SNACK).addAll(mealMapping(mondaySnack));

        String[] tuesdayBreakfast = request.getParameterValues("tuesday-meal-breakfast");
        String[] tuesdayLunch = request.getParameterValues("tuesday-meal-lunch");
        String[] tuesdayDinner = request.getParameterValues("tuesday-meal-dinner");
        String[] tuesdaySnack = request.getParameterValues("tuesday-meal-snack");

        mealList.get(Days.TUESDAY).get(MealType.BREAKFAST).addAll(mealMapping(tuesdayBreakfast));
        mealList.get(Days.TUESDAY).get(MealType.LUNCH).addAll(mealMapping(tuesdayLunch));
        mealList.get(Days.TUESDAY).get(MealType.DINNER).addAll(mealMapping(tuesdayDinner));
        mealList.get(Days.TUESDAY).get(MealType.SNACK).addAll(mealMapping(tuesdaySnack));

        String[] wednesdayBreakfast = request.getParameterValues("wednesday-meal-breakfast");
        String[] wednesdayLunch = request.getParameterValues("wednesday-meal-lunch");
        String[] wednesdayDinner = request.getParameterValues("wednesday-meal-dinner");
        String[] wednesdaySnack = request.getParameterValues("wednesday-meal-snack");

        mealList.get(Days.WEDNESDAY).get(MealType.BREAKFAST).addAll(mealMapping(wednesdayBreakfast));
        mealList.get(Days.WEDNESDAY).get(MealType.LUNCH).addAll(mealMapping(wednesdayLunch));
        mealList.get(Days.WEDNESDAY).get(MealType.DINNER).addAll(mealMapping(wednesdayDinner));
        mealList.get(Days.WEDNESDAY).get(MealType.SNACK).addAll(mealMapping(wednesdaySnack));

        String[] thursdayBreakfast = request.getParameterValues("thursday-meal-breakfast");
        String[] thursdayLunch = request.getParameterValues("thursday-meal-lunch");
        String[] thursdayDinner = request.getParameterValues("thursday-meal-dinner");
        String[] thursdaySnack = request.getParameterValues("thursday-meal-snack");

        mealList.get(Days.THURSDAY).get(MealType.BREAKFAST).addAll(mealMapping(thursdayBreakfast));
        mealList.get(Days.THURSDAY).get(MealType.LUNCH).addAll(mealMapping(thursdayLunch));
        mealList.get(Days.THURSDAY).get(MealType.DINNER).addAll(mealMapping(thursdayDinner));
        mealList.get(Days.THURSDAY).get(MealType.SNACK).addAll(mealMapping(thursdaySnack));

        String[] fridayBreakfast = request.getParameterValues("friday-meal-breakfast");
        String[] fridayLunch = request.getParameterValues("friday-meal-lunch");
        String[] fridayDinner = request.getParameterValues("friday-meal-dinner");
        String[] fridaySnack = request.getParameterValues("friday-meal-snack");

        mealList.get(Days.FRIDAY).get(MealType.BREAKFAST).addAll(mealMapping(fridayBreakfast));
        mealList.get(Days.FRIDAY).get(MealType.LUNCH).addAll(mealMapping(fridayLunch));
        mealList.get(Days.FRIDAY).get(MealType.DINNER).addAll(mealMapping(fridayDinner));
        mealList.get(Days.FRIDAY).get(MealType.SNACK).addAll(mealMapping(fridaySnack));

        String[] saturdayBreakfast = request.getParameterValues("saturday-meal-breakfast");
        String[] saturdayLunch = request.getParameterValues("saturday-meal-lunch");
        String[] saturdayDinner = request.getParameterValues("saturday-meal-dinner");
        String[] saturdaySnack = request.getParameterValues("saturday-meal-snack");

        mealList.get(Days.SATURDAY).get(MealType.BREAKFAST).addAll(mealMapping(saturdayBreakfast));
        mealList.get(Days.SATURDAY).get(MealType.LUNCH).addAll(mealMapping(saturdayLunch));
        mealList.get(Days.SATURDAY).get(MealType.DINNER).addAll(mealMapping(saturdayDinner));
        mealList.get(Days.SATURDAY).get(MealType.SNACK).addAll(mealMapping(saturdaySnack));

        mealList.forEach((day, mapForADay) -> {
            if (day.equals(Days.MONDAY)) {
                mapForADay.forEach((mealType, listForAType) -> {
                    listForAType.forEach(meal -> {
                        WeeklyMealDetail detail = new WeeklyMealDetail(weeklyId, meal, mealType, day.getValue());
                        weeklyList.add(detail);
                    });
                });
            } else {
                mapForADay.forEach((mealType, listForAType) -> {
                    listForAType.forEach(meal -> {
                        WeeklyMealDetail detail = new WeeklyMealDetail(weeklyId, meal, mealType, day.getValue());
                        WeeklyMealDetail addedDetail = weeklyList.stream()
                                .filter(added -> (added.getMeal().getId() == detail.getMeal().getId())
                                && added.getType().equals(detail.getType())
                                && (added.getDaysOfTheWeek() & detail.getDaysOfTheWeek()) == 0)
                                .findFirst()
                                .orElse(null);
                        if (addedDetail != null) {
                            int currentDaysOfTheWeek = addedDetail.getDaysOfTheWeek();
                            addedDetail.setDaysOfTheWeek(currentDaysOfTheWeek + day.getValue());
                        } else {
                            weeklyList.add(detail);
                        }
                    });
                });
            }
        });
//        String str = "";
//        for (WeeklyMealDetail detail : weeklyList) {
//            str += String.format("<p>%d, %s, %s, %d</p>", detail.getPlanMealId(), detail.getMeal().getName(), detail.getType().toString(), detail.getDaysOfTheWeek());
//        }

        weeklyDetailDao.removeByWeeklyId(weeklyId);
        int result = weeklyDetailDao.persist(weeklyList);
        System.out.println(result);
        String msg = result > 0 ? "Successfully" : "Failed";
        request.getSession().setAttribute("msg", msg);
        response.sendRedirect("main?route=admin-get-weekly-detail&weeklyId=" + weeklyId);
    }
}
