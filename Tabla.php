<?php
class Keys95
{
    private $claves, $id;

    public function __construct($claves, $id=null){
        $this->claves = $claves;
        if ($id) {
            $this->id = $id;
        }
    }

    public static function obtener(){
        global $mysqli;
        $resultado = $mysqli->query("SELECT id, claves FROM claves95");
        return $resultado->fetch_all(MYSQLI_ASSOC);
    }
}