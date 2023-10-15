package com.fgrutsch.demo.components

import androidx.compose.runtime.Composable
import com.fgrutsch.pf.PFModifier
import com.fgrutsch.pf.components.*
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Text

@Composable
fun FormField(
    label: String,
    value: String?,
    required: Boolean = true,
    onChange: (String) -> Unit,
) {
    val inputValid = required && !value.isNullOrEmpty()

    PFFormGroup {
        PFFormGroupLabel {
            PFFormLabelText {
                Text(label)
            }
            if (required) PFFormLabelRequired { Text("*") }
        }
        PFFormGroupControl {
            PFFormControl(if (inputValid) emptyList() else listOf(PFModifier.ERROR)) {
                Input(InputType.Text) {
                    value(value ?: "")
                    onInput {
                        onChange(it.value)
                    }
                }

                if (!inputValid) {
                    PFFormControlUtilities {
                        PFFormControlIcon(listOf(PFModifier.STATUS)) {
                            PFIcon("fa-exclamation-circle")
                        }
                    }
                }
            }

            if (!inputValid) {
                PFFormHelperText {
                    PFHelperText {
                        PFHelperTextItem(listOf(PFModifier.ERROR)) {
                            PFHelperTextItemText {
                                Text("This field is required.")
                            }
                        }
                    }
                }
            }
        }
    }
}
