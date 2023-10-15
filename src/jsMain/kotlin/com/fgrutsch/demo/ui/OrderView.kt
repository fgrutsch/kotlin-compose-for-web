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
fun OrderView(vm: OrderViewModel) {
    when (vm.state) {
        OrderState.Init -> vm.onCommand(OrderCommand.Get)

        OrderState.Loaded -> {
            PFCard {
                PFCardBody {

                    // In real application we would create a generic solution to handle forms
                    PFForm(listOf(PFModifier.HORIZONTAL)) {
                        FormField(
                            label = "Billing Name",
                            value = vm.formEntity?.billingName,
                            onChange = { vm.onCommand(OrderCommand.MutateEntity { copy(billingName = it) }) }
                        )
                        FormField(
                            label = "Date",
                            value = vm.formEntity?.date,
                            onChange = { vm.onCommand(OrderCommand.MutateEntity { copy(date = it) }) }
                        )
                        FormField(
                            label = "Amount",
                            value = vm.formEntity?.amount?.toString(),
                            onChange = { vm.onCommand(OrderCommand.MutateEntity { copy(amount = it.toLongOrNull() ?: 0) }) }
                        )
                        FormField(
                            label = "Status",
                            value = vm.formEntity?.status,
                            onChange = { vm.onCommand(OrderCommand.MutateEntity { copy(status = it) }) }
                        )
                        FormField(
                            label = "Payment Method",
                            value = vm.formEntity?.paymentMethod,
                            onChange = { vm.onCommand(OrderCommand.MutateEntity { copy(paymentMethod = it) }) }
                        )
                        FormField(
                            label = "Product",
                            value = vm.formEntity?.product,
                            onChange = { vm.onCommand(OrderCommand.MutateEntity { copy(product = it) }) }
                        )

                        PFFormGroup {
                            PFFormGroupControl {
                                PFFormActions {
                                    PFButton(
                                        listOf(PFModifier.PRIMARY),
                                        {
                                            type(ButtonType.Button)
                                            onClick { vm.onCommand(OrderCommand.Save) }
                                            if (!vm.entityChanged) disabled()
                                        }
                                    ) {
                                        val label = if (vm.id == null) "Create" else "Update"
                                        Text(label)
                                    }

                                    PFButton(
                                        attrs = {
                                            type(ButtonType.Button)
                                            onClick { vm.onCommand(OrderCommand.Revert) }
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
