package com.major.git;

public class JgitDiff {
	public JgitDiff() {  
    }  
    static String URL="D:/cfrManage/ConfigFile/.git";  
    static Git git;  
    public static Repository repository;  
    public static void main(String[] args) {  
        JgitDiff jgitDiff = new JgitDiff();  
          
        jgitDiff.diffMethod("Child","Parent");  
    }  
    /* 
     *  
     */  
    public void diffMethod(String Child, String Parent){  
        try {  
            git=Git.open(new File("D:/cfrManage/ConfigFile/.git"));  
        } catch (IOException e1) {  
            e1.printStackTrace();  
        }  
        repository=git.getRepository();  
        ObjectReader reader = repository.newObjectReader();  
        CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();  
      
        try {  
            ObjectId old = repository.resolve(Child + "^{tree}");  
            ObjectId head = repository.resolve(Parent+"^{tree}");  
                    oldTreeIter.reset(reader, old);  
            CanonicalTreeParser newTreeIter = new CanonicalTreeParser();  
            newTreeIter.reset(reader, head);  
            List<DiffEntry> diffs= git.diff()  
                    .setNewTree(newTreeIter)  
                    .setOldTree(oldTreeIter)  
                    .call();  
              
             ByteArrayOutputStream out = new ByteArrayOutputStream();  
                DiffFormatter df = new DiffFormatter(out);  
                df.setRepository(git.getRepository());  
              
            for (DiffEntry diffEntry : diffs) {  
                 df.format(diffEntry);  
                 String diffText = out.toString("UTF-8");  
                 System.out.println(diffText);  
               //  out.reset();  
            }  
        } catch (IncorrectObjectTypeException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (GitAPIException e) {  
            e.printStackTrace();  
        }  
    }
}
