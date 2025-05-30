
package SteppingStones.Veiw;

import SteppingStones.Model.Recipe;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author Chris Huckins
 */
public class ViewRecipesView extends JFrame {
    private final JButton exitButton;

    public ViewRecipesView(Recipe recipe) throws HeadlessException {
        setLayout(null);
        setSize(580, 630);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Add Recipe");
        setResizable(false);
        
        JTextArea recipeArea = new JTextArea();
        recipeArea.setText(recipe.getDiplayString());
        recipeArea.setBounds(30, 30, 520, 510);
        add(recipeArea);
        
        exitButton = new JButton("Close");
        exitButton.setBounds(470, 550, 75, 30);
        exitButton.addActionListener((ActionEvent e) -> {
            System.out.println("Close button clicked");
            dispose();
        });
        
        add(exitButton);
    }
    
}
