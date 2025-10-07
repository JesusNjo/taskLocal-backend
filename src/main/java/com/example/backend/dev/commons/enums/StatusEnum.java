package com.example.backend.dev.commons.enums;

import java.util.HashMap;
import java.util.Map;

public enum StatusEnum {
    ACTIVE,
    DISABLED,
    INACTIVE,
    LOCKED,
    EXPIRED,
    CLOSED,
    OPEN,
    UNKNOWN,
    DEPRECATED,
    PENDING,
    PROCESSING,
    DONATED,
    CANCELED,
    REJECTED,
    APPROVED,
    COMPLETED,
    CONCILIATION,
    ERROR,
    HOLDING,
    PROCESSED,
    ABORTED,
    BLOCKED,
    RESIGNED,
    NOT_FOUND,
    REACTIVATED,
    ROLLBACK,
    ASSIGNED,
    AUTHORIZED
    ;


    private static final Map<StatusEnum, String> translations = new HashMap<>();

    static {
        translations.put(StatusEnum.ACTIVE, "Activo");
        translations.put(StatusEnum.DISABLED, "Deshabilitado");
        translations.put(StatusEnum.INACTIVE, "Inactivo");
        translations.put(StatusEnum.LOCKED, "Bloqueado");
        translations.put(StatusEnum.BLOCKED, "Bloqueado");
        translations.put(StatusEnum.EXPIRED, "Expirado");
        translations.put(StatusEnum.CLOSED, "Cerrado");
        translations.put(StatusEnum.OPEN, "Abierto");
        translations.put(StatusEnum.UNKNOWN, "Desconocido");
        translations.put(StatusEnum.DEPRECATED, "Obsoleto");
        translations.put(StatusEnum.PENDING, "Pendiente");
        translations.put(StatusEnum.PROCESSING, "Procesando");
        translations.put(StatusEnum.DONATED, "Donado");
        translations.put(StatusEnum.CANCELED, "Cancelado");
        translations.put(StatusEnum.REJECTED, "Rechazado");
        translations.put(StatusEnum.APPROVED, "Aprobado");
        translations.put(StatusEnum.COMPLETED, "Completado");
        translations.put(StatusEnum.CONCILIATION, "Conciliación");
        translations.put(StatusEnum.ERROR, "Error");
        translations.put(StatusEnum.HOLDING, "Retenido");
        translations.put(StatusEnum.REACTIVATED, "Reactivado");
        translations.put(StatusEnum.PROCESSED, "Procesado");
        translations.put(StatusEnum.ABORTED, "Abortado");
        translations.put(StatusEnum.ROLLBACK , "Revertido");
        translations.put(StatusEnum.ASSIGNED , "Asignado");
        translations.put(StatusEnum.AUTHORIZED , "Autorizado");
    }

    public String getTranslation() {
        return translations.get(this);
    }

    public StatusEnum toggleActiveInactive() {
        return this == ACTIVE ? INACTIVE
                : this == INACTIVE ? ACTIVE
                : this;
    }
}
