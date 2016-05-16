package com.shop.comparators;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import com.shop.model.dto.Product;

public class PriceComparatorAscending implements Comparator<Product> {

	@Override
	public int compare(Product o1, Product o2) {
		return o1.getPrice() - o2.getPrice();
	}

	@Override
	public Comparator<Product> reversed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Product> thenComparing(Comparator<? super Product> other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> Comparator<Product> thenComparing(
			Function<? super Product, ? extends U> keyExtractor,
			Comparator<? super U> keyComparator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U extends Comparable<? super U>> Comparator<Product> thenComparing(
			Function<? super Product, ? extends U> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Product> thenComparingInt(
			ToIntFunction<? super Product> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Product> thenComparingLong(
			ToLongFunction<? super Product> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Product> thenComparingDouble(
			ToDoubleFunction<? super Product> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}
}
