//main try block  
try    
{    
    statement 1;    
    statement 2;    
//try catch block within another try block  
    try    
    {    
        statement 3;    
        statement 4;    
//try catch block within nested try block  
        try    
        {    
            statement 5;    
            statement 6;    
     }    
        catch(Exception e2)    
        {    
//exception message  
        }    
  
    }    
    catch(Exception e1)    
    {    
//exception message  
    }    
}    
//catch block of parent (outer) try block  
catch(Exception e3)    
{    
//exception message  
}    
....   