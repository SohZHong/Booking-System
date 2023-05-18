package utils;

import com.toedter.calendar.IDateEvaluator;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomDateChooser extends JDateChooser {
    private List<DateRange> dateRanges;

    public CustomDateChooser() {
        super();
        this.dateRanges = new ArrayList<>();
        this.setDateFormatString("dd/MM/yyyy");
    }

    public void addDateRange(Date startDate, Date endDate) {
        this.dateRanges.add(new DateRange(startDate, endDate));
        updateDateEvaluator();
    }

    public void clearDateRanges() {
        this.dateRanges.clear();
        updateDateEvaluator();
    }

    private void updateDateEvaluator() {
        IDateEvaluator evaluator = new IDateEvaluator() {
            @Override
            public boolean isSpecial(Date date) {
                return false;
            }

            @Override
            public Color getSpecialForegroundColor() {
                return null;
            }

            @Override
            public Color getSpecialBackroundColor() {
                return Color.RED;
            }

            @Override
            public String getSpecialTooltip() {
                return null;
            }

            @Override
            public boolean isInvalid(Date date) {
                for (DateRange range : dateRanges) {
                    if (date.after(
                        range.getStartDate()) && 
                            date.before(range.getEndDate())
                        ) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Color getInvalidForegroundColor() {
                return Color.RED;
            }

            @Override
            public Color getInvalidBackroundColor() {
                return Color.RED;
            }

            @Override
            public String getInvalidTooltip() {
                return "Date is not available for selection";
            }
        };
        this.getJCalendar().getDayChooser().addDateEvaluator(evaluator);
        this.repaint(); //repaint to show new invalid date ranges
    }

    private class DateRange {
        private Date startDate;
        private Date endDate;

        public DateRange(Date startDate, Date endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Date getStartDate() {
            return startDate;
        }

        public Date getEndDate() {
            return endDate;
        }
    }
}
