package com.copperleaf.forms.compose.bulma.form

import androidx.compose.runtime.Composable
import com.copperleaf.forms.compose.bulma.controls.checkbox
import com.copperleaf.forms.compose.bulma.controls.checkboxesEnum
import com.copperleaf.forms.compose.bulma.controls.checkboxesOneOf
import com.copperleaf.forms.compose.bulma.controls.control
import com.copperleaf.forms.compose.bulma.controls.dropdownEnum
import com.copperleaf.forms.compose.bulma.controls.dropdownOneOf
import com.copperleaf.forms.compose.bulma.controls.radioButtonEnum
import com.copperleaf.forms.compose.bulma.controls.radioButtonOneOf
import com.copperleaf.forms.compose.bulma.controls.switch
import com.copperleaf.forms.compose.bulma.elements.element
import com.copperleaf.forms.compose.controls.ControlRenderer
import com.copperleaf.forms.compose.elements.UiElementRenderer
import com.copperleaf.forms.compose.elements.element
import com.copperleaf.forms.compose.elements.submit
import com.copperleaf.forms.compose.elements.toggleDebug
import com.copperleaf.forms.compose.form.BasicForm
import com.copperleaf.forms.compose.form.ComposeFormConfig
import com.copperleaf.forms.compose.form.Registered
import com.copperleaf.forms.core.ArrayControl
import com.copperleaf.forms.core.BooleanControl
import com.copperleaf.forms.core.Button
import com.copperleaf.forms.core.Categorization
import com.copperleaf.forms.core.Category
import com.copperleaf.forms.core.Group
import com.copperleaf.forms.core.HorizontalLayout
import com.copperleaf.forms.core.IntegerControl
import com.copperleaf.forms.core.Label
import com.copperleaf.forms.core.NumberControl
import com.copperleaf.forms.core.ObjectControl
import com.copperleaf.forms.core.StringControl
import com.copperleaf.forms.core.VerticalLayout
import com.copperleaf.forms.core.ui.UiElement
import com.copperleaf.forms.core.vm.FormViewModel

@Composable
public fun BulmaForm(
    viewModel: FormViewModel,
    config: ComposeFormConfig = ComposeFormConfig(
        elements = UiElement.bulmaDefaults(),
        controls = UiElement.Control.bulmaDefaults(),
        designSystem = BulmaDesignSystem(),
    ),
) {
    config.designSystem.column {
        BasicForm(viewModel, config)
    }
}


public fun UiElement.Control.Companion.bulmaDefaults(): List<Registered<UiElement.Control, ControlRenderer>> =
    listOf(
        // text fields
        StringControl.control(),

        // single-select
        StringControl.dropdownEnum(),
        StringControl.dropdownOneOf(),
        StringControl.radioButtonEnum(),
        StringControl.radioButtonOneOf(),

        // multi-select
        ArrayControl.checkboxesEnum(),
        ArrayControl.checkboxesOneOf(),

        // number controls
        IntegerControl.control(),
        NumberControl.control(),

        // boolean controls
        BooleanControl.checkbox(),
        BooleanControl.switch(),

        // composite controls
        ObjectControl.control(),
        ArrayControl.control(),
    )

public fun UiElement.Companion.bulmaDefaults(): List<Registered<UiElement.ElementWithChildren, UiElementRenderer>> =
    listOf(
        VerticalLayout.element(),
        HorizontalLayout.element(),

        Label.element(),

        Group.element(),
        Categorization.element(),
        Category.element(),

        Button.submit(),
        Button.toggleDebug(),
    )