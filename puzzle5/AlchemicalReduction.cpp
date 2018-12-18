#include <fstream>
#include <iostream>
#include <stack>

//Solution part 1; first reduction
std::stack<char> firstReduction() {
    std::stack<char> charStack;
    char letter;

    std::fstream fin("input.txt", std::fstream::in);
    while (fin >> letter) {
        if (charStack.empty()) {
            charStack.push(letter);
            continue;
        }

        char previous = charStack.top();
        charStack.pop();

        bool equals = char(std::tolower(previous)) == char(std::tolower(letter));
        bool differentCase = (std::islower(previous) && std::isupper(letter)) ||
                             (std::isupper(previous) && std::islower(letter));

        if (!(equals && differentCase)) {
            charStack.push(previous);
            charStack.push(letter);
        }
    }

    std::cout << "Units after reductions: " <<charStack.size();
    return charStack;
}

int main() {
    auto polyStack = firstReduction();
    
}