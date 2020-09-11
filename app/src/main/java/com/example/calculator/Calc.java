package com.example.calculator;

public class Calc {
    public static double[] history = new double[50];


    public double add(double a, double b) {
        double answer = a + b;
        return answer;
    }

    public double sub(double a, double b) {
        double minus = a - b;
        return minus;
    }

    public double mul(double a, double b) {
        double answer = a * b;
        return answer;
    }

    public String div(double a, double b) {
        String answer;
        if (b == 0)
            answer = "Error";
        else
            answer = Double.toString(a / b);
        return answer;
    }

    public double[] getHistory() {
        return history;
    }

    public void setHistory(double[] history) {
        this.history = history;
    }

    public double setHistory(double num, int i) {

        this.history[i] = num;
//        System.out.println(this.history[i]);
        return this.history[i];
    }

    public void clearHistory() {
        for (int i = 0; i < 50; i++) {
            history[i] = 0;
        }
    }

    public double useHistory(int i) {
        return history[i];
    }
}

