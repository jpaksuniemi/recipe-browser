package com.group16.util;

import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;

public class AutoScaler {

    private static final Scale scale = new Scale(1, 1);

    /**
     * Makes the element group scale with window size
     * @param node
     */
    public static void makeScaleable(Node node) {
        node.getTransforms().add(scale);
    }

    public static void setActiveScene(Scene scene) {
        scale.xProperty().bind(Bindings.createDoubleBinding(() ->
                        Math.min(scene.getWidth() / ConstantValues.BASE_WIDTH * 1.4,
                                 scene.getHeight() / ConstantValues.BASE_HEIGHT * 1.4),
                        scene.widthProperty(), scene.heightProperty()
        ));
        scale.yProperty().bind(scale.xProperty());
    }
}
