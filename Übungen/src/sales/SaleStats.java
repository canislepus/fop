package sales;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class SaleStats implements ISaleStatistics{

	@Override
	public void printSales(Collection<Sale<?>> sales) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Predicate<Sale<?>> getMonthFilter(Month month) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predicate<Sale<?>> getTimePeriodFilter(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Sale<?>> getFilteredSalesAsSet(Collection<Sale<?>> sales, Predicate<Sale<?>> filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sale<?>> getSortedSalesList(Collection<Sale<?>> sales, Comparator<Sale<?>> comparator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTotalRevenue(Collection<Sale<?>> sales) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAverageRevenue(Collection<Sale<?>> sales) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getStdDev(Collection<Sale<?>> sales) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
