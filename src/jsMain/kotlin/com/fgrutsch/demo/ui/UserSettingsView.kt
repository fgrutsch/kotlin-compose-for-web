package com.fgrutsch.demo.ui

import androidx.compose.runtime.Composable
import com.fgrutsch.demo.components.FormField
import com.fgrutsch.pf.PFModifier
import com.fgrutsch.pf.components.*
import org.jetbrains.compose.web.attributes.ButtonType
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.attributes.type
import org.jetbrains.compose.web.dom.Text

@Composable
fun UserSettingsView(vm: UserSettingsViewModel) {
    when (vm.state) {
        SettingsState.Init -> vm.onCommand(SettingsCommand.Get)

        SettingsState.Loaded -> {
            PFCard {
                PFCardBody {

                    // In real application we would create a generic solution to handle forms
                    PFForm(listOf(PFModifier.HORIZONTAL)) {
                        FormField(
                            label = "Email",
                            value = vm.formEntity?.email,
                            onChange = { vm.onCommand(SettingsCommand.MutateEntity { copy(email = it) }) }
                        )
                        FormField(
                            label = "Firstname",
                            value = vm.formEntity?.firstName,
                            onChange = { vm.onCommand(SettingsCommand.MutateEntity { copy(firstName = it) }) }
                        )
                        FormField(
                            label = "Lastname",
                            value = vm.formEntity?.lastName,
                            onChange = { vm.onCommand(SettingsCommand.MutateEntity { copy(lastName = it) }) }
                        )

                        PFFormGroup {
                            PFFormGroupControl {
                                PFFormActions {
                                    PFButton(
                                        listOf(PFModifier.PRIMARY),
                                        {
                                            type(ButtonType.Button)
                                            onClick { vm.onCommand(SettingsCommand.Update) }
                                            if (!vm.entityChanged) disabled()
                                        }
                                    ) {
                                        Text("Save")
                                    }

                                    PFButton(
                                        attrs = {
                                            type(ButtonType.Button)
                                            onClick { vm.onCommand(SettingsCommand.Revert) }
                                            if (!vm.entityChanged) disabled()
                                        }
                                    ) {
                                        Text("Revert")
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}
