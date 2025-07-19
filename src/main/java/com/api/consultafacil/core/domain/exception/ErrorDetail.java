package com.api.consultafacil.core.domain.exception;

public record ErrorDetail(String field, String errorMessage, Object rejectedValue) {}
