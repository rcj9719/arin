# arin
Augmented Reality Based Indoor Navigation System
Open up a Windows command prompt.
Change into the directory where your source code is located in the command prompt.
First, create a new repository in this directory git init. This will say "Initialized empty git repository in ....git" (... is the path).
Now you need to tell git about your files by adding them to your repository. Do this with git add filename. If you want to add all your files, you can do git add .
Now that you have added your files and made your changes, you need to commit your changes so git can track them. Type git commit -m "adding files". -m lets you add the commit message in line.
So far, the above steps is what you would do even if you were not using github. They are the normal steps to start a git repository. Remember that git is distributed (decentralized), means you don't need to have a "central server" (or even a network connection), to use git.
Now you want to push the changes to your git repository hosted with github. To you this by telling git to add a remote location, and you do that with this command:
git remote add origin https://github.com/yourusername/your-repo-name.git
Once you have done that, git now knows about your remote repository. You can then tell it to push (which is "upload") your commited files:
git push -u origin master
