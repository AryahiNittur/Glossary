# Glossary

This project is a component-based application that generates a glossary in HTML format from a structured text input file. The glossary includes a top-level index page and separate pages for each term, with links interconnecting terms that appear within definitions.

## Objectives

- Design and implement a realistic, component-based application without a predefined skeleton.
- Use only components from the `components` package and standard Java libraries used in CSE 2221 lectures, labs, and projects.
- Gain experience with precision, maintainability, and adherence to coding standards.

## Features

- **Glossary Index**: Generates a top-level `index.html` file listing all glossary terms in alphabetical order, linking to their respective pages.
- **Term Pages**:
  - Separate HTML pages for each term, displaying the term in **red bold italics** followed by its definition.
  - Hyperlinks within definitions for any terms that appear in the glossary.
- **Batch Processing**: Processes the entire glossary in one execution from a single input file.
- **User-Friendly Input/Output**:
  - Prompts the user for the input file and output folder.
  - Respects user-provided paths without augmenting them or adding file extensions.

## Input Format

The input file must have the following structure:
1. A single term on one line.
2. Its definition on one or more subsequent lines, ending with an empty line.
3. The sequence continues for all terms.

### Example Input (`terms.txt`):
term1 
This is the definition of term1.

term2 
Definition of term2 <br>
includes term1.

term3 
This is term3's definition.


## Output Format

1. **Index HTML (`index.html`)**:
   - Lists all terms in alphabetical order with links to their respective pages.
2. **Term HTML Pages**:
   - Each page is named after the term (e.g., `term1.html`) and includes:
     - The term in **red bold italics**.
     - Its definition.
     - Hyperlinks for any terms that appear in the definition.

