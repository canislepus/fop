package sales;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SaleStats implements ISaleStatistics{

	@Override
	public void printSales(Collection<Sale<?>> sales) {
		Iterator<Sale<?>> it = sales.iterator();
		while(it.hasNext()){
			System.out.println(it.next().toString());
		}
	}

	@Override
	public Predicate<Sale<?>> getMonthFilter(Month month) {
		return (Sale<?> sale) -> (sale.getDate().getMonth() == month);	//Months equal?
	}

	@Override
	public Predicate<Sale<?>> getTimePeriodFilter(LocalDate startDate, LocalDate endDate) {
		return (Sale<?> sale) -> {		//Date within range?
			return (!sale.getDate().isBefore(startDate))
					&& (!sale.getDate().isAfter(endDate));
		};
	}

	@Override
	public Set<Sale<?>> getFilteredSalesAsSet(Collection<Sale<?>> sales, Predicate<Sale<?>> filter) {
		Set<Sale<?>> filtered = sales.stream().filter(filter).collect(Collectors.toSet());
		return filtered;
	}

	@Override
	public List<Sale<?>> getSortedSalesList(Collection<Sale<?>> sales, Comparator<Sale<?>> comparator) {
		List<Sale<?>> sorted = sales.stream().sorted(comparator).collect(Collectors.toList());
		return sorted;
	}

	@Override
	public double getTotalRevenue(Collection<Sale<?>> sales) {
		return sales.stream().mapToDouble(Sale::getPrice).sum();
	}

	@Override
	public double getAverageRevenue(Collection<Sale<?>> sales) {
		return sales.stream().mapToDouble(Sale::getPrice).sum() / (double) sales.size();
	}

	@Override
	public double getStdDev(Collection<Sale<?>> sales) {
		double mu = getAverageRevenue(sales);			//Expected value
		return Math.sqrt(sales.stream()					//Square root of...
				.mapToDouble(Sale::getPrice)			//...(The prices...
				.map((x) -> (Math.pow(x - mu, 2)))		//...minus the expected value) squared.
				.sum() / sales.size());
	}

	
}
