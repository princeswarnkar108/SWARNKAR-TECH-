package com.example.data

data class Question(
    val id: Int,
    val questionText: String,
    val options: List<String>,
    val correctAnswer: String,
    val explanation: String
)

object QuestionProvider {

    fun getQuestions(subject: String, levelIndex: Int): List<Question> {
        return when (subject.lowercase()) {
            "math" -> getMathQuestions(levelIndex)
            "english" -> getEnglishQuestions(levelIndex)
            "hindi" -> getHindiQuestions(levelIndex)
            else -> getMathQuestions(1)
        }
    }

    private fun getMathQuestions(levelIndex: Int): List<Question> {
        return when (levelIndex) {
            1 -> listOf( // Addition
                Question(1, "What is 5 + 3?", listOf("6", "7", "8", "9"), "8", "5 + 3 is 8! Great starting."),
                Question(2, "What is 12 + 7?", listOf("18", "19", "20", "21"), "19", "Adding 12 and 7 gives 19."),
                Question(3, "What is 25 + 14?", listOf("39", "35", "40", "41"), "39", "25 + 14 = 39. Correct!"),
                Question(4, "Solve: 8 + 0 + 7", listOf("14", "15", "16", "17"), "15", "8 + 0 is 8, plus 7 is 15."),
                Question(5, "What is 45 + 55?", listOf("90", "100", "110", "120"), "100", "45 + 55 gives exactly 100!"),
                Question(6, "Find the sum of 18 + 13", listOf("29", "30", "31", "32"), "31", "18 + 13 equals 31."),
                Question(7, "What is 150 + 250?", listOf("350", "400", "450", "500"), "400", "150 combined with 250 is 400."),
                Question(8, "Add these numbers: 34 + 43", listOf("77", "87", "67", "76"), "77", "34 + 43 = 77."),
                Question(9, "What is 9 + 17?", listOf("24", "25", "26", "27"), "26", "9 + 17 makes 26."),
                Question(10, "Solve: 67 + 28", listOf("95", "85", "91", "93"), "95", "67 + 28 is 95! Brilliant.")
            )
            2 -> listOf( // Subtraction
                Question(1, "What is 10 - 4?", listOf("4", "5", "6", "7"), "6", "10 minus 4 leaves 6."),
                Question(2, "What is 18 - 9?", listOf("8", "9", "10", "11"), "9", "18 - 9 is 9. A perfect half!"),
                Question(3, "Subtract: 35 - 12", listOf("23", "25", "22", "24"), "23", "35 - 12 gives 23."),
                Question(4, "What is 50 - 25?", listOf("20", "25", "30", "35"), "25", "Half of 50 is 25."),
                Question(5, "Solve: 100 - 37?", listOf("63", "73", "53", "67"), "63", "100 - 37 is 63."),
                Question(6, "Solve: 42 - 19", listOf("23", "25", "24", "22"), "23", "42 - 19 leaves 23."),
                Question(7, "Find the difference: 88 - 45", listOf("43", "45", "33", "53"), "43", "88 - 45 equals 43."),
                Question(8, "What is 76 - 38?", listOf("36", "38", "40", "42"), "38", "76 - 38 = 38. That's a double!"),
                Question(9, "Solve: 15 - 0", listOf("0", "15", "1", "10"), "15", "Subtracting zero leaves it unchanged."),
                Question(10, "Subtract: 145 - 60", listOf("85", "75", "95", "100"), "85", "145 minus 60 is 85.")
            )
            3 -> listOf( // Multiplication
                Question(1, "What is 4 x 3?", listOf("10", "11", "12", "14"), "12", "Four times three is twelve!"),
                Question(2, "What is 7 x 6?", listOf("36", "42", "48", "40"), "42", "7 x 6 = 42."),
                Question(3, "Find the product: 8 x 9", listOf("72", "81", "64", "76"), "72", "Eight times nine is seventy-two."),
                Question(4, "What is 5 x 11?", listOf("50", "55", "60", "45"), "55", "Multiplying by 5 yields 55."),
                Question(5, "Solve: 12 x 4", listOf("44", "46", "48", "50"), "48", "Twelve times four is forty-eight."),
                Question(6, "What is 15 x 3?", listOf("45", "35", "50", "40"), "45", "Fifteen times three is forty-five."),
                Question(7, "Find the product: 13 x 5", listOf("55", "60", "65", "70"), "65", "13 x 5 = 65."),
                Question(8, "What is 9 x 9?", listOf("79", "81", "89", "90"), "81", "Nine times nine is eighty-one."),
                Question(9, "Solve: 20 x 7", listOf("120", "140", "160", "150"), "140", "20 times 7 is 140."),
                Question(10, "What is 8 x 8?", listOf("56", "60", "64", "72"), "64", "Eight times eight is sixty-four.")
            )
            4 -> listOf( // Division
                Question(1, "What is 12 / 3?", listOf("3", "4", "5", "6"), "4", "12 split into three groups is 4."),
                Question(2, "What is 45 / 5?", listOf("7", "8", "9", "10"), "9", "45 divided by 5 is 9."),
                Question(3, "Solve: 72 / 8", listOf("8", "9", "10", "7"), "9", "Nine times eight is 72, so answer is 9."),
                Question(4, "Divide: 100 / 4", listOf("20", "25", "30", "50"), "25", "One quarter of 100 is 25."),
                Question(5, "What is 56 / 7?", listOf("6", "7", "8", "9"), "8", "7 x 8 = 56, so division is 8."),
                Question(6, "Solve: 90 / 10", listOf("8", "9", "10", "11"), "9", "90 divided by 10 is 9."),
                Question(7, "What is 144 / 12?", listOf("10", "11", "12", "13"), "12", "Twelve twelves is 144."),
                Question(8, "Divide: 63 / 9", listOf("6", "7", "8", "9"), "7", "Nine times seven is sixty-three."),
                Question(9, "Solve: 0 / 15", listOf("0", "15", "1", "Undefined"), "0", "Zero divided by anything is zero."),
                Question(10, "What is 150 / 6?", listOf("20", "22", "25", "30"), "25", "150 divided by 6 is 25.")
            )
            5 -> listOf( // Fractions
                Question(1, "Which of the following is equivalent to half?", listOf("1/4", "2/4", "3/4", "4/4"), "2/4", "2/4 is equal to 0.5 or 1/2!"),
                Question(2, "Solve: 1/4 + 2/4", listOf("3/4", "3/8", "2/4", "4/4"), "3/4", "Keep the denominator, add the numerators."),
                Question(3, "Convert to simplest form: 6/8", listOf("2/3", "3/4", "5/6", "1/2"), "3/4", "Divide both terms by 2 to get 3/4."),
                Question(4, "Which fraction is the largest?", listOf("1/2", "1/4", "1/8", "1/3"), "1/2", "Half is the largest portion here."),
                Question(5, "What is 2/5 of 20?", listOf("4", "6", "8", "10"), "8", "20/5 = 4, then times 2 is 8."),
                Question(6, "Solve: 3/10 + 4/10", listOf("7/10", "7/20", "1/10", "7/100"), "7/10", "3 + 4 is 7 over 10."),
                Question(7, "What is a fraction where numerator is bigger than denominator called?", listOf("Proper", "Improper", "Mixed", "Decimal"), "Improper", "An improper fraction has a larger numerator."),
                Question(8, "Solve: 1 - 2/3", listOf("1/3", "2/3", "3/3", "1/2"), "1/3", "One whole is 3/3; subtracting 2/3 leaves 1/3."),
                Question(9, "What is 1/2 of 1/2?", listOf("1/2", "1/3", "1/4", "1/8"), "1/4", "Half of a half is a quarter (1/4)."),
                Question(10, "Identify the decimal value of 4/5", listOf("0.4", "0.6", "0.8", "0.5"), "0.8", "4 divided by 5 is indeed 0.8.")
            )
            6 -> listOf( // Algebra
                Question(1, "If x + 5 = 12, what is x?", listOf("5", "6", "7", "8"), "7", "Subtract 5 from both sides to get x = 7."),
                Question(2, "Solve for a: 2a = 16", listOf("6", "7", "8", "9"), "8", "Divide 16 by 2 to get a = 8."),
                Question(3, "If 3x - 4 = 11, find x.", listOf("3", "4", "5", "6"), "5", "Add 4 to get 3x = 15, so x = 5."),
                Question(4, "Solve for y: y/3 = 6", listOf("2", "9", "12", "18"), "18", "Multiply both sides by 3 to get 18."),
                Question(5, "Expand the term: 2(x + 4)", listOf("2x + 4", "2x + 8", "x + 8", "2x - 8"), "2x + 8", "Distribute the 2 to both terms."),
                Question(6, "If x = 3, find the value of x^2 + 5", listOf("11", "14", "15", "10"), "14", "3^2 is 9, plus 5 equals 14."),
                Question(7, "Solve: 5x + 3 = 2x + 12", listOf("1", "2", "3", "4"), "3", "Subtract 2x and 3 to get 3x = 9, so x = 3."),
                Question(8, "If x - 7 = -2, what is x?", listOf("5", "-9", "-5", "9"), "5", "-2 + 7 = 5."),
                Question(9, "What is the degree of the expression x^2 + 3x + 1?", listOf("1", "2", "3", "None"), "2", "The highest power (degree) is 2."),
                Question(10, "Solve for m: 4m - 7 = 9", listOf("2", "3", "4", "5"), "4", "4m = 16, which means m = 4.")
            )
            7 -> listOf( // Percentage
                Question(1, "What is 10% of 90?", listOf("9", "10", "19", "90"), "9", "10% of any value is divided by 10."),
                Question(2, "What is 50% of 240?", listOf("100", "120", "140", "150"), "120", "50% represents exact half, which is 120."),
                Question(3, "Express 3/4 as a percentage.", listOf("25%", "50%", "75%", "100%"), "75%", "Three-quarters is equal to 75%."),
                Question(4, "What is 20% of 150?", listOf("25", "30", "35", "40"), "30", "150 x 0.20 = 30."),
                Question(5, "If a toy costs $20 and is 10% off, how much do you save?", listOf("$1", "$2", "$3", "$4"), "$2", "10% of $20 is $2 savings."),
                Question(6, "40 is what percent of 80?", listOf("25%", "40%", "50%", "60%"), "50%", "40 is exactly half of 80, which is 50%."),
                Question(7, "What is 100% of 47?", listOf("47", "100", "1", "0"), "47", "100% is the complete number itself, 47."),
                Question(8, "If score is 15 out of 20, what is the percentage?", listOf("70%", "75%", "80%", "85%"), "75%", "15 / 20 = 0.75, which is 75%."),
                Question(9, "What is 5% of 100?", listOf("0.5", "5", "50", "0.05"), "5", "5% of 100 is exactly 5."),
                Question(10, "If price increased from 100 to 125, what is the percentage increase?", listOf("20%", "25%", "30%", "15%"), "25%", "The absolute increase is 25, which on base 100 is 25%.")
            )
            8 -> listOf( // Trigonometry Basic
                Question(1, "What is the value of sin(30°)?", listOf("0", "1/2", "√3/2", "1"), "1/2", "Sine of 30 degrees is mathematically exactly 0.5 or 1/2."),
                Question(2, "In a right triangle, what is Sine (sin) equal to?", listOf("Opposite / Hypotenuse", "Adjacent / Hypotenuse", "Opposite / Adjacent", "Hypotenuse / Opposite"), "Opposite / Hypotenuse", "Sine is Opposite over Hypotenuse."),
                Question(3, "What is the value of cos(0°)?", listOf("0", "1/2", "1", "Undefined"), "1", "Cosine of 0 degrees is the maximum value, 1."),
                Question(4, "In a right triangle, what is Tangent (tan) equal to?", listOf("Opp/Hyp", "Adj/Hyp", "Opp/Adj", "Adj/Opp"), "Opp/Adj", "Tangent is Opposite over Adjacent."),
                Question(5, "If sin(A) = 3/5 and cos(A) = 4/5, what is tan(A)?", listOf("3/4", "4/3", "3/5", "1"), "3/4", "tan(A) = sin(A) / cos(A) = (3/5) / (4/5) = 3/4."),
                Question(6, "What is the value of sin(90°)?", listOf("0", "0.5", "1", "Undefined"), "1", "Sine reaches its peak value of 1 at 90 degrees."),
                Question(7, "The hypotenuse is always which side of a right-angled triangle?", listOf("Shortest", "Longest", "Vertical", "Horizontal"), "Longest", "The hypotenuse is the side opposite to 90 degrees, always the longest."),
                Question(8, "What is the sum of angles in any triangle?", listOf("90°", "180°", "270°", "360°"), "180°", "All angles in any triangle sum up to 180 degrees."),
                Question(9, "What is the reciprocal of sin(x)?", listOf("cos(x)", "tan(x)", "cosec(x)", "sec(x)"), "cosec(x)", "Cosecant (cosec) is the reciprocal of sine."),
                Question(10, "According to Pythagorean theorem, if legs are 3 and 4, what is hypotenuse?", listOf("5", "6", "7", "25"), "5", "3^2 + 4^2 = 9 + 16 = 25. Square root of 25 is 5!")
            )
            else -> emptyList()
        }
    }

    private fun getEnglishQuestions(levelIndex: Int): List<Question> {
        return when (levelIndex) {
            1 -> listOf( // Alphabet
                Question(1, "Which letter comes directly after 'E'?", listOf("D", "F", "G", "H"), "F", "'F' follows 'E' in ABCs."),
                Question(2, "How many vowels are there in the English alphabet?", listOf("5", "6", "7", "21"), "5", "The 5 vowels are A, E, I, O, U."),
                Question(3, "Is 'Y' generally considered a consonant or vowel?", listOf("Consonant", "Vowel", "Number", "Punctuation"), "Consonant", "'Y' is structurally a consonant, though sometimes works as a vowel sound."),
                Question(4, "Which of these is a CAPITAL letter?", listOf("a", "b", "M", "u"), "M", "'M' is the capital form."),
                Question(5, "Complete the alphabet sequence: X, Y, __", listOf("W", "Z", "A", "V"), "Z", "'Z' is the final letter!"),
                Question(6, "Which letter is a vowel?", listOf("B", "T", "O", "K"), "O", "'O' is one of the vowels."),
                Question(7, "Which letter comes before 'J'?", listOf("H", "I", "K", "L"), "I", "'I' comes immediately before 'J'."),
                Question(8, "How many letters in total are in the English alphabet?", listOf("24", "25", "26", "28"), "26", "There are exactly 26 letters."),
                Question(9, "Which letter is between P and R?", listOf("Q", "O", "S", "T"), "Q", "P - Q - R. So 'Q' is in between."),
                Question(10, "Which of these letters is a consonant?", listOf("A", "E", "I", "K"), "K", "'K' is a consonant.")
            )
            2 -> listOf( // Words
                Question(1, "What is the opposite of 'Hot'?", listOf("Warm", "Cold", "Wet", "Sunny"), "Cold", "The opposite of hot is cold!"),
                Question(2, "Select the correct spelling:", listOf("Recieve", "Receive", "Receve", "Recive"), "Receive", "Remember: 'i' before 'e' except after 'c'."),
                Question(3, "What is the plural of 'Child'?", listOf("Childs", "Childrens", "Children", "Childes"), "Children", "The plural of child is children."),
                Question(4, "Which word describes a action?", listOf("Run", "Beautiful", "Quickly", "Apple"), "Run", "'Run' is an action verb."),
                Question(5, "Identify the opposite of 'Big':", listOf("Large", "Small", "Tall", "Heavy"), "Small", "Small is the opposite of big."),
                Question(6, "Which of these means 'Speedy'?", listOf("Slow", "Fast", "Quiet", "Heavy"), "Fast", "Fast is synonymous with speedy."),
                Question(7, "What is the plural of 'Foot'?", listOf("Foots", "Feets", "Feet", "Footes"), "Feet", "'Feet' is correct."),
                Question(8, "Identify the correct spelling for a fruit:", listOf("Benana", "Banana", "Banna", "Banaana"), "Banana", "B-A-N-A-N-A is correct."),
                Question(9, "What represents the opposite of 'Day'?", listOf("Morning", "Night", "Sun", "Evening"), "Night", "The opposite of day is night."),
                Question(10, "Select the word that represents a color:", listOf("Tree", "Blue", "Car", "High"), "Blue", "Blue is a primary color.")
            )
            3 -> listOf( // Grammar
                Question(1, "Identify the noun in: 'The cat is sleeping.'", listOf("The", "cat", "is", "sleeping"), "cat", "'cat' is a person, place, or thing (noun)."),
                Question(2, "Complete the sentence: 'He ___ a good student.'", listOf("am", "is", "are", "be"), "is", "Singular pronoun 'He' takes the verb 'is'."),
                Question(3, "Select the pronoun in: 'She went to the marketplace.'", listOf("She", "went", "marketplace", "the"), "She", "'She' replaces a noun, so it's a pronoun."),
                Question(4, "Which word is an adjective? 'The red apple was delicious.'", listOf("apple", "red", "was", "the"), "red", "'red' describes the noun apple."),
                Question(5, "What is the past tense of 'Go'?", listOf("Goes", "Going", "Went", "Gone"), "Went", "The simple past of go is went."),
                Question(6, "Choose the correct article: 'I saw ___ elephant.'", listOf("a", "an", "the", "none"), "an", "'Elephant' starts with a vowel sound, so use 'an'."),
                Question(7, "What is the conjunction in: 'I like milk and cookies.'", listOf("and", "like", "milk", "I"), "and", "'and' joins word concepts."),
                Question(8, "Identify the adverb: 'She sings beautifully.'", listOf("She", "sings", "beautifully", "beautiful"), "beautifully", "'beautifully' describes how she sings."),
                Question(9, "Identify the proper noun: 'We visited Paris last June.'", listOf("visited", "Paris", "last", "June"), "Paris", "Paris is a specific city, a proper noun."),
                Question(10, "What is the preposition in: 'The book is on the table.'", listOf("on", "book", "is", "table"), "on", "'on' describes the spatial relationship.")
            )
            4 -> listOf( // Tenses
                Question(1, "Which tense is used here: 'I will write a book.'?", listOf("Past", "Present", "Future", "Continuous"), "Future", "'will' indicates a future action."),
                Question(2, "Complete: 'Yesterday, she ___ a cup of tea.'", listOf("drinks", "drinking", "drank", "will drink"), "drank", "'Yesterday' indicates simple past tense (drank)."),
                Question(3, "Identify the present continuous tense sentence:", listOf("I study math.", "I will study math.", "I studyed math.", "I am studying math."), "I am studying math.", "Present continuous uses (am/is/are) + verb-ing."),
                Question(4, "Choose the correct verb: 'Water ___ at 100 degrees Celsius.'", listOf("boil", "boils", "boiling", "boiled"), "boils", "Universal truths use simple present tense (boils)."),
                Question(5, "Which helper verb is used for future tense?", listOf("has", "was", "will", "did"), "will", "'will' or 'shall' is the future marker."),
                Question(6, "Identify past perfect tense helper verb:", listOf("have", "has", "had", "been"), "had", "Past perfect uses 'had' + past participle."),
                Question(7, "Complete: 'They ___ playing football now.'", listOf("is", "am", "are", "was"), "are", "Plural 'They' in present continuous takes 'are'."),
                Question(8, "What is the past participle of 'Write'?", listOf("Wrote", "Writing", "Written", "Writes"), "Written", "Write -> wrote -> written."),
                Question(9, "Complete: 'She has ___ her homework.'", listOf("do", "did", "done", "doing"), "done", "Present perfect uses has + past participle (done)."),
                Question(10, "Identify the tense: 'I have been living here for years.'", listOf("Present Perfect", "Present Perfect Continuous", "Past Continuous", "Future Perfect"), "Present Perfect Continuous", "has/have + been + verb-ing.")
            )
            5 -> listOf( // Sentence correction
                Question(1, "Which sentence is grammatically correct?", listOf("He do not like mangoes.", "He does not like mangoes.", "He don't likes mangoes.", "He does not likes mangoes."), "He does not like mangoes.", "Singular 'He' uses 'does not', followed by base verb."),
                Question(2, "Correct this sentence: 'Me and Him are playing.'", listOf("Him and me are playing.", "He and I are playing.", "He and me is playing.", "I and him am playing."), "He and I are playing.", "Subject subject form 'He and I' is correct."),
                Question(3, "Find the error: 'Where does you live?'", listOf("Where", "does", "you", "live"), "does", "'you' takes the auxiliary verb 'do', not 'does'."),
                Question(4, "Choose the most correct option:", listOf("Each of the students have finished.", "Each of the students has finished.", "Each student have finished.", "Each students has finished."), "Each of the students has finished.", "'Each' is singular, so it takes 'has'."),
                Question(5, "Fix the punctuation: 'its a beautiful day'", listOf("Its a beautiful day.", "It's a beautiful day.", "its a beautiful day!", "Its' a beautiful day."), "It's a beautiful day.", "Contraction for 'It is' is 'It's'."),
                Question(6, "Which sentence uses 'their/there/they're' correctly?", listOf("They're standing over there with their dog.", "There standing over their with they're dog.", "Their standing over there with they're dog.", "They're standing over their with there dog."), "They're standing over there with their dog.", "They're (they are), there (location), their (possession)."),
                Question(7, "Correct: 'She sing very sweet.'", listOf("She sings very sweet.", "She sings very sweetly.", "She sing very sweetly.", "She sings very sweeter."), "She sings very sweetly.", "Verb (sings) must be modified by an adverb (sweetly)."),
                Question(8, "Which of these is correct?", listOf("The scissors is sharp.", "The scissors are sharp.", "Scissors has sharp.", "The scissor are sharp."), "The scissors are sharp.", "'Scissors' is a plural-only noun."),
                Question(9, "Find the mistake: 'I am looking forward to meet you.'", listOf("looking", "forward", "meet", "you"), "meet", "Phrasal verb 'looking forward to' takes a gerund (meeting)."),
                Question(10, "Which sentence has correct word order?", listOf("Never I have seen such a beautiful view.", "Never have I seen such a beautiful view.", "I have never saw such a beautiful view.", "Never saw I such beautiful view."), "Never have I seen such a beautiful view.", "Inversion occurs when sentence starts with negative adverb 'Never'.")
            )
            6 -> listOf( // Vocabulary
                Question(1, "What is a synonym of 'Happy'?", listOf("Sad", "Angry", "Cheerful", "Tired"), "Cheerful", "Cheerful shares a similar meaning to happy."),
                Question(2, "What is the antonym of 'Brave'?", listOf("Courageous", "Cowardly", "Strong", "Heroic"), "Cowardly", "Cowardly is the opposite of brave."),
                Question(3, "What does the word 'Brief' mean?", listOf("Long", "Short", "Complex", "Clear"), "Short", "'Brief' means short in duration or length."),
                Question(4, "Choose a synonym for 'Huge':", listOf("Tiny", "Gigantic", "Heavy", "Deep"), "Gigantic", "Gigantic means extremely large/huge."),
                Question(5, "What is the opposite of 'Ancient'?", listOf("Old", "Modern", "Historic", "Antique"), "Modern", "'Modern' is the antonym of ancient (very old)."),
                Question(6, "A person who writes books is an:", listOf("Artist", "Author", "Actor", "Architect"), "Author", "An author writes books."),
                Question(7, "What is a synonym for 'Fast'?", listOf("Slow", "Swift", "Careful", "Heavy"), "Swift", "'Swift' is synonymous with fast."),
                Question(8, "What does 'Generous' mean?", listOf("Selfish", "Kind and giving", "Smart", "Wealthy"), "Kind and giving", "A generous person is helpful and gives freely."),
                Question(9, "Select the word that means 'to need/require':", listOf("Desire", "Demand", "Request", "Require"), "Require", "Require means to need something as a condition."),
                Question(10, "What is the opposite of 'Victory'?", listOf("Success", "Defeat", "Win", "Gain"), "Defeat", "Defeat is the antonym of victory.")
            )
            else -> emptyList()
        }
    }

    private fun getHindiQuestions(levelIndex: Int): List<Question> {
        return when (levelIndex) {
            1 -> listOf( // वर्णमाला
                Question(1, "हिंदी वर्णमाला में कितने स्वर होते हैं?", listOf("9", "11", "13", "15"), "11", "हिंदी में मुख्य रूप से 11 स्वर (अ से औ) होते हैं।"),
                Question(2, "'क' वर्ग के बाद कौन सा वर्ग आता है?", listOf("च वर्ग", "ट वर्ग", "त वर्ग", "प वर्ग"), "च वर्ग", "कवर्ग के बाद चवर्ग (च, छ, ज, झ, ञ) आता है।"),
                Question(3, "निम्न में से कौन सा स्वर दीर्घ स्वर है?", listOf("अ", "इ", "उ", "आ"), "आ", "आ, ई, ऊ दीर्घ स्वर हैं।"),
                Question(4, "हिंदी वर्णमाला में 'ज्ञ' क्या है?", listOf("स्वर", "व्यंजन", "संयुक्त व्यंजन", "अयोगवाह"), "संयुक्त व्यंजन", "ज्ञ दो व्यंजनों (ज् + ञ) के योग से बनता है।"),
                Question(5, "'अ' और 'आ' का उच्चारण स्थान क्या है?", listOf("कंठ", "तालु", "मूर्धा", "दंत"), "कंठ", "अ और आ कंठ से बोले जाते हैं।"),
                Question(6, "वर्णों के व्यवस्थित समूह को क्या कहते हैं?", listOf("शब्द", "वाक्य", "वर्णमाला", "भाषा"), "वर्णमाला", "वर्णों के व्यवस्थित समूह को वर्णमाला कहते हैं।"),
                Question(7, "निम्नलिखित में से स्पर्श व्यंजन कौन-सा है?", listOf("य", "स", "क", "ह"), "क", "क से म तक स्पर्श व्यंजन होते हैं।"),
                Question(8, "अंतस्थ व्यंजन कौन से हैं?", listOf("श, ष, स", "य, र, ल, व", "च, छ, ज", "क, ख, ग"), "य, र, ल, व", "य, र, ल, व अंतस्थ व्यंजन कहलाते हैं।"),
                Question(9, "हिंदी भाषा की लिपि क्या है?", listOf("रोमन", "देवनागरी", "गुरमुखी", "फ़ारसी"), "देवनागरी", "हिंदी देवनागरी लिपि में लिखी जाती है।"),
                Question(10, "अनुस्वार का चिन्ह कौन सा है?", listOf("बिंदु (ं)", "चंद्रबिंदु (ँ)", "विसर्ग (ः)", "हलंत (्)"), "बिंदु (ं)", "अनुस्वार को शिरोरेखा के ऊपर बिंदु (ं) से दर्शाते हैं।")
            )
            2 -> listOf( // शब्द
                Question(1, "सही वर्तनी वाला शब्द चुनिए:", listOf("अतिथी", "अतिथि", "अतीथी", "अतीथि"), "अतिथि", "सही शब्द 'अतिथि' (मेहमान) है।"),
                Question(2, "दो वर्णों के मेल से बने सार्थक समूह को क्या कहते हैं?", listOf("वर्ण", "शब्द", "वाक्य", "पद"), "शब्द", "वर्णों के सार्थक मेल से शब्द बनता है।"),
                Question(3, "'कमल' किस प्रकार का शब्द है?", listOf("रूढ़", "यौगिक", "योगरूढ़", "विदेशी"), "रूढ़", "कमल के टुकड़े करने पर सार्थक अर्थ नहीं निकलता, यह रूढ़ है।"),
                Question(4, "निम्नलिखित में से शुद्ध शब्द कौन सा है?", listOf("पुरस्कार", "परुस्कार", "पुरष्कार", "प्रुस्कार"), "पुरस्कार", "सही वर्तनी 'पुरस्कार' है।"),
                Question(5, "मछली का तत्सम शब्द कौन सा है?", listOf("मीन", "मत्स्य", "मछरिया", "जलचर"), "मत्स्य", "मछली को संस्कृत (तत्सम) में मत्स्य कहते हैं।"),
                Question(6, "उत्पत्ति के आधार पर शब्द के कितने भेद हैं?", listOf("2", "3", "4", "5"), "4", "तत्सम, तद्भव, देशज और विदेशी (4 प्रमुख भेद)।"),
                Question(7, "निम्नलिखित में से देशज शब्द कौन सा है?", listOf("स्कूल", "लोटा", "अग्नि", "मयूर"), "लोटा", "लोटा स्थानीय बोली से आया देशज शब्द है।"),
                Question(8, "सूर्य का तद्भव रूप क्या होगा?", listOf("दिनकर", "सूरज", "रवि", "भानु"), "सूरज", "सूर्य का सरल तद्भव रूप 'सूरज' है।"),
                Question(9, "शुद्ध वर्तनी वाला शब्द कौन सा है?", listOf("उज्वल", "उज्ज्वल", "उजवल", "उज्वल"), "उज्ज्वल", "उज्ज्वल में दो बार आधा 'ज' (ज्ज्) होता है।"),
                Question(10, "जो शब्द संस्कृत से बिना किसी बदलाव के हिंदी में आए हैं, वे क्या कहलाते हैं?", listOf("तत्सम", "तद्भव", "देशज", "विदेशी"), "तत्सम", "संस्कृत के मूल शब्द तत्सम कहलाते हैं।")
            )
            3 -> listOf( // व्याकरण
                Question(1, "संज्ञा के कितने मुख्य भेद होते हैं?", listOf("2", "3", "5", "6"), "3", "संज्ञा के मुख्य रूप से 3 भेद हैं (व्यक्तिवाचक, जातिवाचक, भाववाचक)।"),
                Question(2, "'राम दिल्ली गया।' इस वाक्य में 'राम' क्या है?", listOf("सर्वनाम", "क्रिया", "संज्ञा", "विशेषण"), "संज्ञा", "राम एक व्यक्ति का नाम है, इसलिए यह संज्ञा है।"),
                Question(3, "संज्ञा के स्थान पर प्रयुक्त होने वाले शब्दों को क्या कहते हैं?", listOf("क्रिया", "विशेषण", "सर्वनाम", "अव्यय"), "सर्वनाम", "जो संज्ञा की जगह आए उसे सर्वनाम कहते हैं (जैसे - वह, तुम)।"),
                Question(4, "'घोड़ा तेज दौड़ता है।' - इस वाक्य में विशेषण क्या है?", listOf("घोड़ा", "तेज", "दौड़ता", "है"), "तेज", "दौड़ने की विशेषता बताने वाला शब्द 'तेज' क्रियाविशेषण/विशेषण है।"),
                Question(5, "क्रिया के जिस रूप से कार्य के समय का पता चले, उसे क्या कहते हैं?", listOf("लिंग", "वचन", "काल", "कारक"), "काल", "समय का बोध कराने वाले को काल (Tense) कहते हैं।"),
                Question(6, "'लड़के खेल रहे हैं।' - इस वाक्य में वाक्य का कर्ता कौन है?", listOf("लड़के", "खेल", "रहे", "हैं"), "लड़के", "कार्य को करने वाला कर्ता (लड़के) है।"),
                Question(7, "हिंदी में वचन के कितने प्रकार होते हैं?", listOf("एक", "दो", "तीन", "चार"), "दो", "हिंदी में दो वचन होते हैं: एकवचन और बहुवचन।"),
                Question(8, "'वह लिखता है।' - इस वाक्य में क्रिया क्या है?", listOf("वह", "लिखता", "है", "लिखता है"), "लिखता है", "लिखना एक कार्य है, इसलिए यह क्रिया है।"),
                Question(9, "निम्न में से स्त्रीलिंग शब्द कौन सा है?", listOf("आम", "नदी", "पेड़", "घर"), "नदी", "नदियाँ सामान्यतः स्त्रीलिंग होती हैं।"),
                Question(10, "'राम ने रावण को बाण से मारा।' - इसमें 'ने' किस कारक का चिह्न है?", listOf("कर्म", "करण", "कर्ता", "अपादान"), "कर्ता", "कर्ता कारक का चिह्न 'ने' है।")
            )
            4 -> listOf( // पर्यायवाची
                Question(1, "'जल' का पर्यायवाची शब्द है:", listOf("अनिल", "पानी", "हवा", "पावक"), "पानी", "जल का सबसे सरल पर्यायवाची पानी, नीर, वारि है।"),
                Question(2, "'सूर्य' का पर्यायवाची इनमें से कौन सा नहीं है?", listOf("रवि", "दिनकर", "सुधाकर", "भानु"), "सुधाकर", "सुधाकर चंद्रमा को कहते हैं, सूर्य को नहीं।"),
                Question(3, "गंगा का पर्यायवाची शब्द बताइए:", listOf("सरिता", "तरंगिणी", "भागीरथी", "यमुना"), "भागीरथी", "भागीरथी गंगा का प्रसिद्ध पर्यायवाची नाम है।"),
                Question(4, "'हवा' का पर्यायवाची शब्द कौन सा है?", listOf("अनल", "पवन", "सलिल", "जलद"), "पवन", "पवन हवा का पर्यायवाची है। (अनल = आग, सलिल = जल)"),
                Question(5, "'आँख' का पर्यायवाची शब्द चुनें:", listOf("नेत्र", "कान", "हाथ", "गला"), "नेत्र", "नेत्र, चक्षु, लोचन आँख के पर्यायवाची हैं।"),
                Question(6, "'बादल' का पर्यायवाची शब्द कौन सा है?", listOf("नीरज", "जलद", "जलज", "पंकज"), "जलद", "जलद (जल देने वाला) बादल का पर्यायवाची है।"),
                Question(7, "'घर' का एक अन्य पर्यायवाची शब्द क्या है?", listOf("आवास", "गगन", "तरु", "सरोज"), "आवास", "आवास, गृह, निकेतन घर के पर्यायवाची हैं।"),
                Question(8, "'अग्नि' का पर्यायवाची बताइए:", listOf("पावक", "जल", "पवन", "व्योम"), "पावक", "पावक आग (अग्नि) का पर्यायवाची है।"),
                Question(9, "'जंगल' का पर्यायवाची कौन सा है?", listOf("वन", "नगर", "मकान", "सड़क"), "वन", "वन, कानन, विपिन जंगल के पर्यायवाची हैं।"),
                Question(10, "'कमल' का पर्यायवाची शब्द है:", listOf("पंकज", "जलद", "वारिद", "पवन"), "पंकज", "पंकज (कीचड़ में जनमा) कमल को कहते हैं।")
            )
            5 -> listOf( // विलोम
                Question(1, "'दिन' का विलोम शब्द क्या है?", listOf("सुबह", "शाम", "रात", "दोपहर"), "रात", "दिन का उल्टा (वलोम) रात होता है।"),
                Question(2, "'अमृत' का विलोम शब्द कौन सा है?", listOf("सुधा", "विष", "जल", "दूध"), "विष", "अमृत का विलोम विष (जहर) है।"),
                Question(3, "'सत्य' का विलोम शब्द बताएं:", listOf("झूठ", "असत्य", "पाप", "अधर्म"), "असत्य", "सत्य का विपरीतार्थक असत्य होता है।"),
                Question(4, "'उदय' शब्द का विलोम क्या होगा?", listOf("अस्त", "डूबना", "नष्ट", "लाल"), "अस्त", "जैसे सूर्य उदय होता है और फिर अस्त होता है।"),
                Question(5, "'अंधकार' का विलोम शब्द चुनें:", listOf("उजाला", "प्रकाश", "रोशनी", "चमक"), "प्रकाश", "अंधकार का सटीक विलोम प्रकाश है।"),
                Question(6, "'आदर' का विलोम शब्द क्या है?", listOf("सम्मान", "सत्कार", "अनादर", "प्रेम"), "अनादर", "आदर का विलोम अनादर या निरादर होता है।"),
                Question(7, "'सुख' का विलोम शब्द क्या होगा?", listOf("हर्ष", "आनन्द", "दुःख", "कष्ट"), "दुःख", "सुख का विलोम दुःख है।"),
                Question(8, "'ज्ञान' का विलोम शब्द है:", listOf("अज्ञान", "बुद्धि", "विद्वान", "मूर्ख"), "अज्ञान", "ज्ञान का विलोम अज्ञान है।"),
                Question(9, "'ऊपर' का विलोम क्या होगा?", listOf("नीचे", "दूर", "पास", "अंदर"), "नीचे", "ऊपर का विपरीतार्थक नीचे है।"),
                Question(10, "'जीत' का विलोम शब्द क्या है?", listOf("पराजय", "हार", "जीतना", "कोई नहीं"), "हार", "जीत का विलोम हार (या पराजय) होता है।")
            )
            6 -> listOf( // वाक्य
                Question(1, "निम्नलिखित वाक्यों में से शुद्ध वाक्य चुनिए:", listOf("राम खाता है भात।", "राम भात खाता है।", "खाता है राम भात।", "भात राम खाता है।"), "राम भात खाता है।", "हिंदी वाक्य संरचना में मुख्य रूप से कर्ता + कर्म + क्रिया होती है।"),
                Question(2, "'वह कल आएगा' किस काल का वाक्य है?", listOf("भूतकाल", "वर्तमान काल", "भविष्य काल", "कोई नहीं"), "भविष्य काल", "'आएगा' से भविष्य में होने वाले कार्य का पता चलता है।"),
                Question(3, "वाक्य के मुख्य रूप से कितने अंग होते हैं?", listOf("दो", "तीन", "चार", "पाँच"), "दो", "वाक्य के दो प्रमुख अंग हैं: उद्देश्य (Subject) और विधेय (Predicate)।"),
                Question(4, "प्रश्न पूछने वाले वाक्यों के अंत में कौन सा विराम चिह्न लगता है?", listOf("पूर्णविराम (।)", "प्रश्नवाचक चिह्न (?)", "अल्पविराम (,)", "विस्मयादिबोधक (!)"), "प्रश्नवाचक चिह्न (?)", "प्रश्न पूछने पर अंत में प्रश्नवाचक चिह्न (?) लगाया जाता है।"),
                Question(5, "शुद्ध वाक्य की पहचान कीजिए:", listOf("मेरे को जाना है।", "मुझे जाना है।", "मैंने जाना है।", "मुझको जाना है।"), "मुझे जाना है।", "व्याकरणिक दृष्टि से 'मुझे जाना है' शुद्ध वाक्य है।"),
                Question(6, "'अरे! कितना सुंदर फूल है।' यह कैसा वाक्य है?", listOf("प्रश्नवाचक", "आज्ञावाचक", "विस्मयादिबोधक", "निषेधवाचक"), "विस्मयादिबोधक", "'अरे!' और विस्मयबोधक चिह्न (!) होने से यह विस्मयादिबोधक वाक्य है।"),
                Question(7, "दो या दो से अधिक शब्दों के सार्थक मेल को क्या कहते हैं?", listOf("अक्षर", "शब्द", "वाक्य", "कारक"), "वाक्य", "शब्दों का सार्थक संघ जिससे पूर्ण भाव प्रकट हो, वाक्य कहलाता है।"),
                Question(8, "शुद्ध वाक्य चुनें:", listOf("पेड़ से पत्ता गिरा।", "पेड़ में से पत्ता गिरा।", "पेड़ पर पत्ता गिरा।", "पेड़ का पत्ता गिरा।"), "पेड़ से पत्ता गिरा।", "अपादान कारक का प्रयोग 'पेड़ से पत्ता गिरा' में सही है।"),
                Question(9, "'सीता गा रही है।' यह वाक्य किस काल का है?", listOf("सामान्य वर्तमान", "अपूर्ण वर्तमान", "भूतकाल", "भविष्य काल"), "अपूर्ण वर्तमान", "कार्य अभी चल रहा है, इसलिए अपूर्ण (या निरंतर) वर्तमान है।"),
                Question(10, "'मैंने चाय नहीं पी।' यह किस प्रकार का वाक्य है?", listOf("विधिवाचक", "निषेधवाचक", "इच्छावाचक", "संकेतवाचक"), "निषेधवाचक", "कार्य न होने (नहीं) का बोध कराने वाले वाक्य निषेधवाचक कहलाते हैं।")
            )
            else -> emptyList()
        }
    }
}
