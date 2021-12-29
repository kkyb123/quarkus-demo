package com.kkyb.quarkusdemo.error;

public class AppException extends RuntimeException {

    private final Problem problem;

    public AppException(Problem problem) {
        super(problem != null && problem.detail() != null ? problem.detail() : "");
        this.problem = problem;
    }

    public AppException(Problem problem, String message) {
        super(message);
        this.problem = problem;
    }

    public AppException(Problem problem, String message, Throwable cause) {
        super(message, cause);
        this.problem = problem;
    }

    public AppException(Problem problem, Throwable cause) {
        super(cause);
        this.problem = problem;
    }

    protected AppException(Problem problem, String message, Throwable cause,
                           boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.problem = problem;
    }

    public Problem getProblem() {
        return problem;
    }
}
