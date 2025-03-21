package io.github.edricchan03.androidx.recyclerview.ktx

import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ListAdapterUtilsTest : DescribeSpec({

    describe("itemCallback") {
        it("should create an ItemCallback") {
            val areItemsTheSame = mockk<(Any, Any) -> Boolean> {
                every { this@mockk(any(), any()) } returns false
            }
            val areContentsTheSame = mockk<(Any, Any) -> Boolean> {
                every { this@mockk(any(), any()) } returns false
            }
            val getChangePayload = mockk<(Any, Any) -> Any?> {
                every { this@mockk(any(), any()) } returns null
            }

            val callback = itemCallback(
                areItemsTheSame = areItemsTheSame,
                areContentsTheSame = areContentsTheSame,
                getChangePayload = getChangePayload
            )

            callback.areItemsTheSame(Any(), Any())
            verify {
                areItemsTheSame(any(), any())
            }

            callback.areContentsTheSame(Any(), Any())
            verify {
                areContentsTheSame(any(), any())
            }
            callback.getChangePayload(Any(), Any())
            verify {
                getChangePayload(any(), any())
            }
        }
    }
})
