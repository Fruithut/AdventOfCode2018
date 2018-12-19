#include <fstream>
#include <iostream>
#include <stack>
#include <vector>
#include <map>
#include <limits>

std::stack<char> getInputToStack(std::string path) {
    std::stack<char> charStack;
    char next;
    std::fstream fin(path, std::fstream::in);
    while (fin >> next) charStack.push(next);
    return charStack;
}

std::stack<char> reduce(std::stack<char>& polymer) {
    std::stack<char> reducedStack;
    while (!polymer.empty()) {
        if (reducedStack.empty()) {
            reducedStack.push(polymer.top());
            polymer.pop();
        }

        char previous = reducedStack.top();
        reducedStack.pop();
        char next = polymer.top();
        polymer.pop();

        bool equals = char(std::tolower(previous)) == char(std::tolower(next));
        bool differentCase = previous < next || previous > next;

        if (!(equals && differentCase)) {
            reducedStack.push(previous);
            reducedStack.push(next);
        }
    }
    return reducedStack;
}

std::stack<char> firstReduction(std::stack<char>& initialStack) {
    std::stack<char> reduced = reduce(initialStack);
    std::cout << "Units first reduction: " << reduced.size() << std::endl;
    return reduced;
}

std::stack<char> secondReduction(std::stack<char>& firstStepStack) {
    std::string alphabet = "abcdefghijklmnopqrstuvwxyz";
    int minimalSize = std::numeric_limits<int>::max();
    std::stack<char> finalStack;

    for (char alpha : alphabet) {
        std::stack<char> polyCopy = firstStepStack;

        //Filter step
        std::stack<char> filteredPoly;
        while (!polyCopy.empty()) {
            char current = polyCopy.top();
            polyCopy.pop();
            if (!(alpha == std::tolower(current))) {
                filteredPoly.push(current);
            }
        }
        //Find best reduction
        std::stack<char> finalStack = reduce(filteredPoly);
        if (finalStack.size() < minimalSize) {
            minimalSize = finalStack.size();
            finalStack = filteredPoly;
        }
    }
    std::cout << "Units after second reduction: " << minimalSize;
    return finalStack;
}

int main() {
    auto inputStack = getInputToStack("input.txt");
    auto firstReducedStack = firstReduction(inputStack);
    auto finalReducedStack = secondReduction(firstReducedStack);
}