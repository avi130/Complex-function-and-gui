# monhe-matalot
**The func Momon(String s) init a Monom from a String *(without multiplication marks between numbers or space in the String )* :
    X && x are acceptable. For example:
    {"x", "5X", "7.2x^3", "-4.5"} is good
    {" x", "5*x", "7..2x^3", "4.5^2x"} is bad !!
If the user entered incorrect input, he function will show an error (works with try& catch) and will not calculate the monom.

**The equals func Compares two monoms in approximate of epsilon.

**The func Polynom(String s) taking the string and makes a polynomial from it.
If some of the input is incorrect the function will show an error
(works with try& catch) and will not calculate the Polynom. (The rules are same as in the Monom).

**There are functions for: adding ,subtracting and multiplying and more both for monomers and polynomials

**The toString() func retuns the monom/polynomial In the order in which they were inserted / written and not by size of powers.

**Next to any function ther are explanation for what the function does and what are the inputs & outputs.

**In the ROOT&Momon func if the input is wrong the func throws"Exception". That why in the Tester we use try&catch.
