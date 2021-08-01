package core;

import model.Order;
import shared.Shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OnlineShop implements Shop {

    private ArrayList<Order> test;

    public OnlineShop() {
        this.test = new ArrayList<>();
    }

    @Override
    public void add(Order order) {
        this.test.add(order);
    }

    @Override
    public Order get(int index) {

        validateIndex(index);
            return this.test.get(index);

    }

    private void validateIndex(int index) {
        if (index < 0 || index >= this.size() || this.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int indexOf(Order order) {

        for (int i = 0; i < this.test.size(); i++) {
            if (this.test.get(i).getId() == order.getId()) {
                return i;
            }
        }
        return -1;

    }

    @Override
    public Boolean contains(Order order) {
        for (Order o : test) {
            if (o.getId() == order.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean remove(Order order) {
        return this.test.remove(order);
    }

    @Override
    public Boolean remove(int id) {
        for (Order o : test) {
            if (o.getId() == id) {
                this.test.remove(o);
                return true;
            }
        }
        return false;
    }

    @Override
    public void set(int index, Order order) {


     validateIndex(index);
         this.test.add(index,order);

    }

    @Override
    public void set(Order oldOrder, Order newOrder) {
        this.test.set(this.indexOf(oldOrder), newOrder);

    }

    @Override
    public void clear() {

        this.test.clear();

    }

    @Override
    public Order[] toArray() {
        Order[] arr = new Order[this.size()];
        return this.test.toArray(arr);
    }

    @Override
    public void swap(Order first, Order second) {

        int firstIndex = indexOf(first);
        int secondIndex = indexOf(second);
        if (firstIndex != -1 && secondIndex != -1) {
            Collections.swap(this.test, firstIndex, secondIndex);
        } else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public List<Order> toList() {
        return new ArrayList<>(this.test);
    }

    @Override
    public void reverse() {
        Collections.reverse(this.test);
    }

    @Override
    public void insert(int index, Order order) {
      validateIndex(index);
            this.test.add(index,order);
    }

    @Override
    public Boolean isEmpty() {
        return this.test.isEmpty();
    }

    @Override
    public int size() {
        return this.test.size();
    }
}
