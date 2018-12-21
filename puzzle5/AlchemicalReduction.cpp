#include <fstream>
#include <iostream>
#include <stack>
#include <limits>
using namespace std;

stack<char> getInputToStack(string path) {
    stack<char> charStack;
    char next;
    fstream fin(path, fstream::in);
    while (fin >> next) charStack.push(next);
    return charStack;
}

stack<char> reduce(stack<char>& polymer) {
    stack<char> reducedStack;
    while (!polymer.empty()) {
        if (reducedStack.empty()) {
            reducedStack.push(polymer.top());
            polymer.pop();
        }

        char previous = reducedStack.top();
        reducedStack.pop();
        char next = polymer.top();
        polymer.pop();

        bool equals = char(tolower(previous)) == char(tolower(next));
        bool differentCase = previous < next || previous > next;

        if (!(equals && differentCase)) {
            reducedStack.push(previous);
            reducedStack.push(next);
        }
    }
    return reducedStack;
}

stack<char> firstReduction(stack<char>& initialStack) {
    stack<char> reduced = reduce(initialStack);
    cout << "Units first reduction: " << reduced.size() << endl;
    return reduced;
}

stack<char> secondReduction(stack<char>& firstStepStack) {
    string alphabet = "abcdefghijklmnopqrstuvwxyz";
    int minimalSize = numeric_limits<int>::max();
    stack<char> finalStack;

    for (char alpha : alphabet) {
        stack<char> polyCopy = firstStepStack;

        //Filter step
        stack<char> filteredPoly;
        while (!polyCopy.empty()) {
            char current = polyCopy.top();
            polyCopy.pop();
            if (!(alpha == tolower(current))) {
                filteredPoly.push(current);
            }
        }
        //Find best reduction
        stack<char> finalStack = reduce(filteredPoly);
        if (finalStack.size() < minimalSize) {
            minimalSize = finalStack.size();
            finalStack = filteredPoly;
        }
    }
    cout << "Units after second reduction: " << minimalSize;
    return finalStack;
}

int main() {
    auto inputStack = getInputToStack("input.txt");
    auto firstReducedStack = firstReduction(inputStack);
    auto finalReducedStack = secondReduction(firstReducedStack);
}