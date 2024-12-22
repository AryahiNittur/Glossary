# RSS_Glossary

This project is a Java program designed to convert an XML RSS (version 2.0) feed from a given URL into multiple linked HTML output files. These files are organized as a glossary, making the content easy to navigate and understand.

## Features

- **Glossary Index**: Generates a top-level `index.html` file listing all glossary terms in alphabetical order. Each term links to its own dedicated page.
- **Term Pages**: Creates separate HTML pages for each term and its definition.
  - Terms are styled in **red bold italics** and precede their definitions.
  - Glossary terms appearing in definitions are hyperlinked to their respective pages.
- **Batch Processing**: Processes all input data at once, producing all necessary HTML files in a single run.
- **User Input**: The program prompts the user for:
  - The input file path (relative or absolute).
  - The output folder path (must already exist).

## Input Format

The input file must follow a specific structure:
1. A single term on one line.
2. Its definition on one or more subsequent lines, ending with an empty line.
3. The sequence continues for all terms.

## Output

- **Top-Level Index**: `index.html` lists all terms in alphabetical order, linking to their individual pages.
- **Term Pages**: Each term has a separate HTML file named `term.html` (e.g., `Term1.html`), containing:
  - The term in **red bold italics**.
  - Its associated definition.
  - Hyperlinks for any terms appearing within definitions.
