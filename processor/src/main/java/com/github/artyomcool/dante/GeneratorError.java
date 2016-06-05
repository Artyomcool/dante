package com.github.artyomcool.dante;

import javax.lang.model.element.Element;

class GeneratorError {

    private final Element element;
    private final String error;

    public GeneratorError(Element element, String error) {

        this.element = element;
        this.error = error;
    }

    public Element getElement() {
        return element;
    }

    public String getError() {
        return error;
    }
}
