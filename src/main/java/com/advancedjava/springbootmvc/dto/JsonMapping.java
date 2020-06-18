package com.advancedjava.springbootmvc.dto;

public interface JsonMapping {
    interface OnlyPrimitiveFields {
    }

    interface ProductWithCategory extends OnlyPrimitiveFields {
    }

    interface ProductWithProvider extends OnlyPrimitiveFields {
    }

    interface ProductComplete extends OnlyPrimitiveFields, ProductWithCategory, ProductWithProvider {
    }
}
