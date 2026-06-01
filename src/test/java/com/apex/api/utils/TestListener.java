package com.apex.api.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("\n==========================================================================");
        System.out.println("🚀 STARTING TEST: " + result.getMethod().getMethodName());
        System.out.println("📝 Description:   " + result.getMethod().getDescription());
        System.out.println("==========================================================================");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ TEST PASSED:  " + result.getMethod().getMethodName());
        System.out.println("⏱️  Duration:   " + (result.getEndMillis() - result.getStartMillis()) + " ms");
        System.out.println("==========================================================================\n");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.err.println("❌ TEST FAILED:  " + result.getMethod().getMethodName());
        System.err.println("🚨 Error Cause:  " + result.getThrowable().getMessage());
        System.err.println("==========================================================================\n");
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("\n🏁 CORE TEST SUITE EXECUTION COMMENCING...");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("\n🏆 ALL TESTS CONCLUDED.");
        System.out.println("📊 Passed: " + context.getPassedTests().size() +
                " | Failed: " + context.getFailedTests().size() +
                " | Skipped: " + context.getSkippedTests().size() + "\n");
    }
}