GRAMATICAS
=================

## Índice 📚
- [Terminales](#terminales)
- [No Terminales](#noterminales)
- [Producciones](#producciones)

<div id='terminales'/>

## Terminales
   
   | **NOMBRE** | **SIMBOLO **|  **NOMBRE** | **SIMBOLO** |
   |-------|---------|----------|-------------|
   |`CONJUUNTO`  | CONJ | `COMILLA` | " 
   | `DIP`        |   ~  | `COMA`    | ,
   |`ASIG`       |  -    | `COMILLASIMPLE` | '
   | `NACION`     |  >   | `INVERBARRA`    | \
   | `LLAVEI`    | {     | `ASCII`   | \[!-$&<-=@/]
   | `LLAVED`    | }      | `TODO `  | \[^.\*]
   | `DPUNTOS`   | :   | `PUNTO` | .
   | `INTERR` | ?  |  `PTCOMA` | ;
   | `BARRA` | \|  | `MAS` | +
   | `POR`   |  *  | `PORC` | %%
   | `ENTERO`  | \[0-9]  |  `RAN` | (\[a-zA-Z_])\[a-zA-Z0-9_]* 
   | `ALFAMAY` | \[A-Z]   | `ALFAMIN`  | \[a-z]

<div id='noterminales'/>

## No terminales

   | **NOMBRE**    |    **NOMBRE**  |    **NOMBRE**   |
   |---------------|----------------|-----------------|
   |`ini`          | `expresion`    | `expresion2 `   |
   | `conjuntos`   |   `conjuntos`  | `listainstrucciones`|
   |`instrucciones`|  `n_exp`       | `alfabeto`      | 
   | `conj`        |  `conj2`       | `instruccion`   | 
   | `conbinacion` | `comilla`      | `xdxdxd`        |
   | `lect`        | `lect2`        |   |


<div id='producciones'/>

## Producciones
`ini -> instrucciones`

`instrucciones -> LLAVEI  listainstrucciones LLAVED `

`listainstrucciones-> listainstrucciones instruccion  |  instruccion`

`instruccion -> 
   conj 
 | n_exp 
 | PORC PORC
 | lect
 | error PTCOMA `

`conj ->
    CONJUNTO DPUNTOS RAN ASIG NACION  conjuntos PTCOMA `

`conjuntos -> 
    ALFAMIN DIP ALFAMIN
 |  ALFAMAY DIP ALFAMAY 
 |  ENTERO  DIP ENTERO   
 |  ASCII   DIP ASCII
 |  xdxdxd`

`xdxdxd: -> 
    ALFAMIN  conj2
 |  ALFAMAY  conj2
 |  ENTERO   conj2
 |  ASCII    conj2`


`conj2 ->
    COMA xdxdxd
 |  ALFAMIN 
 |  ALFAMAY 
 |  ENTERO 
 |  ASCII
 | ` 


`n_exp ->
    RAN:b   ASIG NACION  expresion PTCOMA ` 

`expresion ->
    INTERR comilla         
 |  INTERR alfabeto        
 |  POR expresion2          
 |  POR alfabeto            
 |  PUNTO combinacion       
 |  BARRA combinacion      
 |  MAS alfabeto          
 |  MAS expresion2         
 |  INTERR expresion2`

`expresion2::=
    PUNTO combinacion 
 |  BARRA combinacion`  

`combinacion->
    alfabeto expresion  
 |  alfabeto alfabeto  
 |  alfabeto comilla    
 |  expresion alfabeto 
 |  expresion comilla   
 |  expresion expresion 
 |  comilla expresion  
 |  comilla alfabeto   
 |  comilla comilla  ` 

`alfabeto->
    LLAVEI RAN LLAVED `   

`comilla->
    COMILLA RAN COMILLA              
 |  COMILLA ENTERO COMILLA           
 |  COMILLA PUNTO  COMILLA          
 |  COMILLA ASCII  COMILLA          
 |  INVERBARRA COMILLASIMPLE            
 |  INVERBARRA ALFAMIN                 
 |  COMILLA ASIG  COMILLA                
 |  COMILLA DPUNTOS  COMILLA              
 |  COMILLA DIP  COMILLA                 
 |  COMILLA NACION  COMILLA               
 |  COMILLA LLAVEI  COMILLA              
 |  COMILLA LLAVED  COMILLA               
 |  COMILLA INTERR  COMILLA              
 |  COMILLA PTCOMA  COMILLA              
 |  COMILLA BARRA  COMILLA               
 |  COMILLA MAS  COMILLA                 
 |  COMILLA POR  COMILLA                 
 |  COMILLA PORC  COMILLA                 
 |  COMILLA  COMILLA `                       


`lect->
    RAN   DPUNTOS COMILLA lect2 COMILLA PTCOMA `

`lect2->
    TODO ` 
