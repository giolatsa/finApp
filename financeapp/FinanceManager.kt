package com.example.financeapp

class FinanceManager(
    private val lastName: String, 
    private val birthMonth: Int
) {
    // უნიკალური ფორმულა: გვარის ასოების რაოდენობა + დაბადების თვე
    val savingsPercent: Int
        get() = lastName.length + birthMonth

    // ინახავს თუ არა ხელფასი ხარჯებს
    fun isSalaryEnough(model: FinanceModel): Boolean {
        return model.salary >= (model.rent + model.food)
    }

    // ითვლის დანაზოგს
    fun calculateSavings(model: FinanceModel): Double {
        return model.salary * (savingsPercent / 100.0)
    }

    // ითვლის დარჩენილ ბალანსს
    fun calculateRemaining(model: FinanceModel): Double {
        return model.salary - (model.rent + model.food) - calculateSavings(model)
    }
}
