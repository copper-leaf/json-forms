package com.copperleaf.forms.compose.bulma.widgets.bulma

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.copperleaf.forms.compose.LocallyEnabled
import com.copperleaf.forms.compose.bulma.controls.BulmaField
import com.copperleaf.forms.compose.controls.ControlScope
import com.copperleaf.json.pointer.JsonPointerAction
import com.copperleaf.json.pointer.plus
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.dom.CheckboxInput
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.Text

@Composable
public fun ControlScope.checkboxWidget() {
    val currentValue = getTypedValue(false) {
        it.jsonPrimitive.booleanOrNull
    }
    val isEnabled = LocallyEnabled.current

    BulmaField(control.label) {
        CheckboxInput(currentValue) {
            if (!isEnabled) {
                disabled()
            }
            onInput { event -> event.value?.let { updateFormState(it) } }
        }
    }
}

@Composable
public fun ControlScope.switchWidget() {
    val currentValue = getTypedValue(false) {
        it.jsonPrimitive.booleanOrNull
    }
    val isEnabled = LocallyEnabled.current

    BulmaField(control.label) {
        CheckboxInput(currentValue) {
            if (!isEnabled) {
                disabled()
            }
            onInput { event -> event.value?.let { updateFormState(it) } }
        }
    }
}

@Composable
public fun ControlScope.checkboxesWidget(
    getOptions: JsonElement.()->List<Pair<String, String>>
) {
    val selectedValues: List<String> = getTypedValue(emptyList()) {
        if (it == JsonNull) {
            emptyList()
        } else {
            it.jsonArray.map { it.jsonPrimitive.content }
        }
    }
    val allOptions: List<Pair<String, String>> = remember {
        control.schemaConfig.getOptions()
    }

    BulmaField(control.label) {
        allOptions.forEach { (optionValue, optionTitle) ->
            Label(null, { classes("checkbox") }) {
                CheckboxInput(optionValue in selectedValues) {
                    onClick {
                        if (optionValue in selectedValues) {
                            sendFormAction(
                                pointer = dataPointer + "/${selectedValues.indexOf(optionValue)}",
                                action = JsonPointerAction.RemoveValue,
                            )
                        } else {
                            sendFormAction(
                                pointer = dataPointer + "/${selectedValues.size}",
                                action = JsonPointerAction.SetValue(optionValue),
                            )
                        }
                        markAsTouched()
                    }
                    if (!isEnabled) {
                        disabled()
                    }
                }
                Text(" $optionTitle")
            }
        }
    }
}
