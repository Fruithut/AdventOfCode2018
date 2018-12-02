double_count = 0
tripple_count = 0

for index, word in enumerate(open('input.txt', 'r', 1)):
    found_double = False
    found_tripple = False
    for char in word:
        count = word.count(char)
        if (found_double and found_tripple):
                break
        elif (count == 2 and not found_double):
                found_double = True
                double_count += 1
        elif (count == 3 and not found_tripple):
                found_tripple = True
                tripple_count += 1

print('Checksum:', double_count * tripple_count)