class Emp_Account:
    def __init__(self, EmpId, EmpName, EmpAcctBalance):
        self.EmpId = EmpId
        self.EmpName = EmpName
        self.EmpAcctBalance = EmpAcctBalance

    def creditAmount(self, amount):
        self.EmpAcctBalance += amount
        print(f"Credited {amount}. Updated Balance: {self.EmpAcctBalance}")

    def debitAmount(self, amount):
        if amount <= self.EmpAcctBalance:
            self.EmpAcctBalance -= amount
            print(f"Debited {amount}. Updated Balance: {self.EmpAcctBalance}")
        else:
            print("Insufficient funds for the debit.")

    def printFinalBalance(self):
        print(f"Final Balance for {self.EmpName} (EmpId: {self.EmpId}): {self.EmpAcctBalance}")


if __name__ == "__main__":
    # Creating an instance of Emp_Account
    emp1 = Emp_Account(EmpId=1001, EmpName="User1", EmpAcctBalance=10000)

    # Credit amount
    emp1.creditAmount(5000)

    # Debit amount
    emp1.debitAmount(3000)

    # Attempt to debit an amount greater than the balance
    emp1.debitAmount(15000)

    # Print final balance
    emp1.printFinalBalance()
