package se.codeboss.kotest.demo.model

data class Money(val value: Long) {
    fun truncated(): Money = if (this.value % 100 == 19L) this else Money(value / 100 * 100)
}
