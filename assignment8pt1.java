import java.util.ArrayList;
import java.util.List;

interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

class Stock implements Subject {
    private String symbol;
    private double price;
    private List<Observer> investors;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
        this.investors = new ArrayList<>();
    }

    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        if (!investors.contains(observer)) {
            investors.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        investors.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer investor : investors) {
            investor.update(symbol, price);
        }
    }
}

interface Observer {
    void update(String symbol, double price);
}

class Investor implements Observer {
    private String name;
    private List<Stock> stocks;

    public Investor(String name) {
        this.name = name;
        this.stocks = new ArrayList<>();
    }

    public void investIn(Stock stock) {
        if (!stocks.contains(stock)) {
            stocks.add(stock);
            stock.registerObserver(this);
        }
    }

    public void divestFrom(Stock stock) {
        if (stocks.contains(stock)) {
            stocks.remove(stock);
            stock.removeObserver(this);
        }
    }

    @Override
    public void update(String symbol, double price) {
        System.out.println("Hello " + name + "! " + symbol + " price is now " + price);
    }
}

public class assignment8pt1 {
    public static void main(String[] args) {
        Stock appleStock = new Stock("AAPL", 150.0);
        Stock googleStock = new Stock("GOOGL", 2500.0);

        Investor investor1 = new Investor("Alice");
        Investor investor2 = new Investor("Bob");

        investor1.investIn(appleStock);
        investor2.investIn(googleStock);

        appleStock.setPrice(155.0);
        googleStock.setPrice(2600.0);

        investor1.divestFrom(appleStock);

        appleStock.setPrice(160.0);
    }
}
