package com.example.todoapp.data

object QuestionData {
    val questions = listOf(
        Question(
            "What are the main building blocks of an Angular application?",
            listOf(
                "Components, directives, pipes ..",
                "Controllers, factories ..",
                "Views, models, view-models .."
            ),
            0 // Correct answer index
        ),
        Question(
            "Explain the difference between ngOnInit and constructor",
            listOf(
                "Constructor is for dependency injection ..",
                "They are identical and can be used interchangeably",
                "Constructor runs after ngOnInit in the component lifecycle"
            ),
            0
        ),
        Question(
            "How would you create a two-way data binding in Angular?",
            listOf(
                "Using [(ngModel)] syntax",
                "Using @Input() and @Output() separately",
                "Using setTimeout() to synchronize values"
            ),
            0
        )
    )
}
