package com.expense.split;

import com.expense.split.model.Expense;
import com.expense.split.model.User;
import com.expense.split.repository.ExpenseRepository;
import com.expense.split.repository.UserRepository;
import com.expense.split.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class SplitExpensesApplication {

	private final UserRepository userRepository;
	private final ExpenseRepository expenseRepository;
	private final ExpenseService expenseService;

	public static void main(String[] args) {
		SpringApplication.run(SplitExpensesApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			User user1 = new User("Ali", "1234", "ali@mail.com");
			userRepository.save(user1);
			System.out.println("User Created with id: " + user1.getId() + " with username: " + user1.getUsername());
			User user2 = new User("Amir", "4567", "amir@mail.com");
			userRepository.save(user2);
			System.out.println("User Created with id: " + user2.getId() + " with username: " + user2.getUsername());
			User user3 = new User("Mmd", "7890", "mmd@mail.com");
			userRepository.save(user3);
			System.out.println("User Created with id: " + user3.getId() + " with username: " + user3.getUsername());
			User user4 = new User("Sami", "9823", "sami@mail.com");
			userRepository.save(user4);
			System.out.println("User Created with id: " + user4.getId() + " with username: " + user4.getUsername());

			User payer = userRepository.findById(user1.getId()).orElseThrow(() -> new Exception("Payer not found"));
			System.out.printf("Payer retrieved with username: " + payer.getUsername());

			List<User> debtors = userRepository.findAllById(List.of(user2.getId(), user3.getId(), user4.getId()));
			for (User user : debtors) {
				System.out.println("Debtor retrieved with username: " + user.getUsername());
			}

			Expense expense = new Expense();
			expense.setAmount(100.0);
//			expense.setCategory("Dinner");
			expense.setDescription("Dinner at restaurant");
			expense.setPayer(payer);
			expense.setDebtors(debtors);

			expenseRepository.save(expense);
			System.out.println("Expense added to db with id: " + expense.getId());


			System.out.println("############## Here is the retrieved data:");
			List<Expense> expenses = expenseService.getExpensesWithDebtors();
			for (Expense expense1 : expenses) {
				System.out.println("Expense ID: " + expense1.getId());
				System.out.println("Payer: " + expense1.getPayer().getUsername());
				System.out.println("Debtors: ");
				for (User debtor : expense1.getDebtors()) {
					System.out.println(" - " + debtor.getUsername());
				}
			}


		};
	}

}
