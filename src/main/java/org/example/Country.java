package org.example;


public class Country {
    private String name;
    private double area;
    private long population;
    private String capitalName;
    private Long capitalPopulation;

    public Country(String name, double area, long population, String capitalName, Long capitalPopulation) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название страны не может быть пустым");
        }
        if (area <= 0) {
            throw new IllegalArgumentException("Площадь страны должна быть больше 0");
        }
        if (population < 0) {
            throw new IllegalArgumentException("Население страны не может быть отрицательным");
        }

        this.name = name;
        this.area = area;
        this.population = population;

        setCapital(capitalName, capitalPopulation);


    }

    public Country(String name, double area, long population) {
        this(name, area, population, null, 0L);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Название страны не может быть пустым");

        this.name = name;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        if (area <= 0)
            throw new IllegalArgumentException("Площадь должна быть строго больше нуля");

        this.area = area;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        if (population < 0)
            throw new IllegalArgumentException("Население не может быть отрицательным");

        this.population = population;
    }

    public String getCapitalName() {
        return capitalName;
    }

    public void setCapitalName(String capitalName) {
        this.capitalName = capitalName;
    }

    public Long getCapitalPopulation() {
        return capitalPopulation;
    }

    public double getPopulationDensity() {
        if (population <= 0 || area <= 0)
            throw new IllegalStateException("Невозможно вычислить плотность: некорректные данные");

        return population / area;
    }

    public void setCapital(String capitalName, long capitalPopulation) {
        if (capitalName == null) {
            this.capitalName = null;
            this.capitalPopulation = 0L;
            return;
        }

        if (capitalPopulation < 0)
            throw new IllegalArgumentException("Население не может быть отрицательным");

        this.capitalName = capitalName;
        this.capitalPopulation = capitalPopulation;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(": ").append(area).append(" км²");

        if (population > 0) {
            sb.append(", население: ").append(population);
        }

        if (capitalName != null && capitalPopulation > 0) {
            sb.append("\nСтолица: ").append(capitalName)
                    .append(", население: ").append(capitalPopulation);
        }

        return sb.toString();
    }
}
