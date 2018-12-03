from collections import defaultdict

def getInput(filePath: str):
    file_content = []
    for string in open(filePath, 'r', 1):
        values = string.split(" ")
        first_pair = values[2].split(',')
        second_pair = values[3].split('x')
        file_content.append({
            'id': int(values[0][1:]), 
            'x_offset': int(first_pair[0]), 
            'y_offset': int(first_pair[1][:-1]), 
            'width': int(second_pair[0]), 
            'height': int(second_pair[1][:-1])
        })
    return file_content

square_input = getInput('input.txt')

square_inches = defaultdict(list)
    